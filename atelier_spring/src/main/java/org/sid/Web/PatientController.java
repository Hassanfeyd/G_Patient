package org.sid.Web;



import javax.validation.Valid;

import org.sid.Dao.PatientRepository;
import org.sid.Entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PatientController {
	@Autowired
     PatientRepository crud;
	@GetMapping(path = "/")
	public String index() {
		return "welcome";
	}
	@GetMapping(path = "/Index")
	public String Index(Model model , @RequestParam(defaultValue  = "0", name = "page") int page , 
			@RequestParam(name="size" ,defaultValue = "4") int size,
			@RequestParam(name="keyword" ,defaultValue = "" ) String keyword)
	{
		Page<Patient> plist = crud.findByNameContains(keyword,PageRequest.of(page, size));
		model.addAttribute("listpatients",plist.getContent());
		model.addAttribute("pages",new int[ plist.getTotalPages()]);
		model.addAttribute("current",page);
		model.addAttribute("keyword", keyword);

		return "Index";
	}
	
	@GetMapping(path="/deletepatient")
	public String delete(long id,int page ,String keyword)
	{
		crud.deleteById(id);
		return "redirect:/Index?page="+page+"&keyword="+keyword;
		
	}
	@GetMapping(path="/formpatient")
	public String formpatient(Model model)
	{ 
		model.addAttribute("patient", new Patient());
		model.addAttribute("mode","new");
		return"formpatient";
	}
	@PostMapping(path="/savepatient")
	public String savepatient(Model model,@Valid Patient patient , BindingResult bindingResult) {
		if(bindingResult.hasErrors()) return "formpatient";
		crud.save(patient);
		
		return "redirect:/Index";
	}
	@GetMapping(path = "/editpatient")
	public String editpatient(Model model,long id)
	{
		model.addAttribute("patient", crud.findById(id).get());
		model.addAttribute("mode","edit");
		return "formpatient";
	}
	
	
	
}

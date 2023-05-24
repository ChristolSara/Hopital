package fr.greta.Hopital.Hopital.Controller;
import fr.greta.Hopital.Hopital.Model.Patient;
import fr.greta.Hopital.Hopital.Repository.PatientRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@AllArgsConstructor
public class PatientController {
    private PatientRepository patientRepository;

    @GetMapping("/user/index")
    @PreAuthorize("hasRole('USER')")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "0") int p,
                        @RequestParam(name = "size", defaultValue = "4") int s,
                        @RequestParam(name = "keyword", defaultValue = "") String kw
    ) {
        Page<Patient> pagePatients = patientRepository.findByNameContains(kw, PageRequest.of(p, s));
        model.addAttribute("listPatients", pagePatients.getContent());
        model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage", p);
        model.addAttribute("keyword", kw);
        return "patients";
    }

    @GetMapping("/admin/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@RequestParam(
            name = "id") Long id,
                         @RequestParam(name = "keyword", defaultValue = "") String keyword,
                         @RequestParam(name = "page", defaultValue = "0") int page) {
        patientRepository.deleteById(id);
        return "redirect:/user/index?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/user/index";
    }

    @GetMapping("/admin/formPatients")
    @PreAuthorize("hasRole('ADMIN')")
    public String formPatient(Model model) {
        model.addAttribute("patient", new Patient());
        return "formPatients";
    }

    @PostMapping("/admin/savePatient")
    @PreAuthorize("hasRole('ADMIN')")
    public String savePatient(@Valid Patient patient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "formPatients";
        }
        patientRepository.save(patient);
        return "redirect:/user/index?keyword=" + patient.getName();
    }

    @GetMapping("/admin/editPatient")
    @PreAuthorize("hasRole('ADMIN')")
    public String editPatient(Model model, @RequestParam(name = "id") Long id) {
        Patient patient = patientRepository.findById(id).get();
        model.addAttribute("patient", patient);
        return "editPatient";
    }
}
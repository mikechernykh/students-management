package dev.chernykh.studentmanagement.students;

import dev.chernykh.studentmanagement.groups.GroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

/**
 * Controller to manage students.
 */
@AllArgsConstructor
@Controller
@RequestMapping("/students")
public class StudentsController {
    private final StudentService studentService;
    private final GroupRepository groupRepository;

    /**
     * Display a list of students.
     *
     * @param pageable a page implementation instance provided by spring
     * @return model and view
     */
    @GetMapping
    public ModelAndView getStudents(@PageableDefault Pageable pageable) {
        ModelMap model = new ModelMap();
        model.addAttribute(studentService.getAll(pageable));
        return new ModelAndView("students/list", model);
    }

    /**
     * Display a form to create a new student.
     *
     * @return model and view
     */
    @GetMapping("create")
    public ModelAndView createStudentForm() {
        ModelMap model = new ModelMap();
        model.addAttribute(new StudentDto());
        model.addAttribute("groupNames", groupRepository.findNames());
        return new ModelAndView("students/create", model);
    }

    /**
     * Validate and save a new student.
     *
     * @param studentDto dto object to be validated and converted to a student
     * @param result     validation errors if exist
     * @return model and view
     */
    @PostMapping("create")
    public ModelAndView createStudent(@Valid @ModelAttribute StudentDto studentDto, BindingResult result) {
        if (result.hasErrors()) {
            ModelMap model = new ModelMap();
            model.addAttribute("groupNames", groupRepository.findNames());
            return new ModelAndView("students/create", model);
        }
        studentService.create(studentDto);
        return new ModelAndView("redirect:/students");
    }

    /**
     * Display a student with given id.
     *
     * @param id student id
     * @return model and view
     */
    @GetMapping("{id}")
    public ModelAndView viewStudent(@PathVariable long id) {
        ModelMap model = new ModelMap();
        model.addAttribute(studentService.getOne(id));
        return new ModelAndView("students/viewOne", model);
    }

    /**
     * Display a form to edit a student.
     *
     * @param id student id
     * @return model and view
     */
    @GetMapping("{id}/edit")
    public ModelAndView editStudentForm(@PathVariable long id) {
        Student student = studentService.getOne(id);
        ModelMap model = new ModelMap();
        model.addAttribute(student);
        model.addAttribute(new StudentDto(student));
        model.addAttribute("groupNames", groupRepository.findNames());
        return new ModelAndView("students/edit", model);
    }

    /**
     * Validate and save a changed student.
     *
     * @param studentDto dto object to be validated and converted to a student
     * @param result     validation errors if exist
     * @return model and view
     */
    @PostMapping("{id}/edit")
    public ModelAndView editStudent(@Valid @ModelAttribute StudentDto studentDto, BindingResult result) {
        if (result.hasErrors()) {
            ModelMap model = new ModelMap();
            model.addAttribute(studentService.getOne(studentDto.getId()));
            model.addAttribute(studentDto);
            model.addAttribute("groupNames", groupRepository.findNames());
            return new ModelAndView("students/edit", model);
        }

        studentService.update(studentDto);
        return new ModelAndView("redirect:/students/" + studentDto.getId());
    }

    /**
     * Delete the student with given id.
     *
     * @param id student id
     * @return model and view
     */
    @GetMapping("{id}/delete")
    public RedirectView deleteStudent(@PathVariable long id) {
        studentService.delete(id);
        return new RedirectView("/students");
    }
}

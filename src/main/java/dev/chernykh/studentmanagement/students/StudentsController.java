package dev.chernykh.studentmanagement.students;

import dev.chernykh.studentmanagement.errors.EntityNotFoundException;
import dev.chernykh.studentmanagement.groups.Group;
import dev.chernykh.studentmanagement.groups.GroupRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@AllArgsConstructor
@Controller
@RequestMapping("/students")
@Slf4j
public class StudentsController {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    @GetMapping
    public ModelAndView studentList(@PageableDefault Pageable pageable) {
        ModelMap model = new ModelMap();
        model.addAttribute(studentRepository.findAll(pageable));
        return new ModelAndView("students/list", model);
    }

    @GetMapping("create")
    public ModelAndView createStudentForm() {
        ModelMap model = new ModelMap();
        model.addAttribute(new StudentDto());
        model.addAttribute("groupList", groupRepository.findNames());
        return new ModelAndView("students/create", model);
    }

    @PostMapping("create")
    public ModelAndView checkStudentFormAndSave(@Valid @ModelAttribute StudentDto studentDto, BindingResult result) {
        if (result.hasErrors()) {
            ModelMap model = new ModelMap();
            model.addAttribute("groupList", groupRepository.findNames());
            return new ModelAndView("students/create", model);
        }
        Group group = groupRepository.findByNameLike(studentDto.getGroupName());
        Student student = studentDto.toStudent();
        student.setGroup(group);
        studentRepository.save(student);
        return new ModelAndView("redirect:/students");
    }

    @GetMapping("{student:\\d+}")
    public ModelAndView viewStudent(@PathVariable Student student) throws EntityNotFoundException {
        if (student == null) {
            log.error("STUDENTS CONTROLLER viewStudent(): student not found");
            throw new EntityNotFoundException("Student not found");
        }

        ModelMap model = new ModelMap();
        model.addAttribute(student);
        return new ModelAndView("students/viewOne", model);
    }

    @GetMapping("{student:\\d+}/edit")
    public ModelAndView showEditStudentForm(@PathVariable Student student) throws EntityNotFoundException {
        if (student == null) {
            log.error("STUDENTS CONTROLLER showEditStudentForm(): student not found");
            throw new EntityNotFoundException("Student not found");
        }
        StudentDto studentDto = new StudentDto(student);

        ModelMap model = new ModelMap();
        model.addAttribute(student);
        model.addAttribute(studentDto);
        model.addAttribute("groupList", groupRepository.findNames());

        return new ModelAndView("students/edit", model);
    }

    @PostMapping("{student:\\d+}/edit")
    public ModelAndView saveChangedStudentIfValid(@Valid @ModelAttribute StudentDto studentDto, BindingResult result, @PathVariable Student student)
            throws EntityNotFoundException {
        if (student == null) {
            log.error("STUDENTS CONTROLLER saveChangedStudentIfValid(): student not found");
            throw new EntityNotFoundException("Student not found");
        }

        if (result.hasErrors()) {
            ModelMap model = new ModelMap();
            model.addAttribute(student);
            model.addAttribute(studentDto);
            model.addAttribute("groupList", groupRepository.findNames());
            return new ModelAndView("students/edit", model);
        }

        Group group = groupRepository.findByNameLike(studentDto.getGroupName());
        student.setGroup(group);
        studentRepository.save(student);
        return new ModelAndView("redirect:/students/" + student.getId());
    }

    @GetMapping("{student:\\d+}/delete")
    public RedirectView deleteStudent(@PathVariable Student student) throws EntityNotFoundException {
        if (student == null) {
            log.error("STUDENTS CONTROLLER deleteStudent(): student not found");
            throw new EntityNotFoundException("Student not found");
        }
        studentRepository.delete(student);
        return new RedirectView("/students");
    }
}

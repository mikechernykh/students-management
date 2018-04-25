package dev.chernykh.studentmanagement.groups;

import dev.chernykh.studentmanagement.errors.EntityNotFoundException;
import dev.chernykh.studentmanagement.students.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

/**
 * Controller to manage student groups.
 */
@AllArgsConstructor
@Controller
@Slf4j
@RequestMapping("/groups")
public class GroupsController {
    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;

    /**
     * Displaying a list of groups with pagination.
     *
     * @param pageable a page implementation instance provided by spring
     * @param model    a model to display on a view
     * @return model and view name
     */
    @GetMapping
    public ModelAndView groupList(@PageableDefault Pageable pageable, ModelMap model) {
        model.addAttribute(groupRepository.findAll(pageable));
        return new ModelAndView("groups/list", model);
    }

    /**
     * Displaying a form to input group's details.
     *
     * @return model and view name
     */
    @GetMapping("/create")
    public ModelAndView createGroupForm() {
        ModelMap model = new ModelMap();
        model.addAttribute(new GroupDto());
        return new ModelAndView("groups/create", model);
    }

    /**
     * Verifying user-provided data.
     * If data is valid then redirect the user to page displaying the list of groups.
     * Otherwise display the form again.
     *
     * @param groupDto a user-provided data
     * @param result   verification errors if exist
     * @return model and view name
     */
    @PostMapping("/create")
    public ModelAndView checkGroupAndSave(@Valid @ModelAttribute GroupDto groupDto, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("groups/create", HttpStatus.BAD_REQUEST);
        }

        Group group = new Group();
        group.setName(groupDto.getName());
        group.setFacultyName(groupDto.getFacultyName());

        groupRepository.save(group);

        return new ModelAndView("redirect:/groups");
    }

    /**
     * Displaying details of a group with given id.
     *
     * @param group group entity if exists
     * @return model and view name
     * @throws EntityNotFoundException if entity has not been found
     */
    @GetMapping("{group:\\d+}")
    public ModelAndView viewGroup(@PathVariable Group group) throws EntityNotFoundException {
        if (group == null) {
            log.error("GROUPS CONTROLLER viewGroup(): group not found");
            throw new EntityNotFoundException("error.group.notFound");
        }
        ModelMap model = new ModelMap();
        model.addAttribute(group);
        model.addAttribute(studentRepository.findByGroup(group));
        return new ModelAndView("groups/viewOne", model);
    }

    /**
     * Deleting a group with given id if one exists.
     *
     * @param group an entity to be deleted
     * @return redirect view name
     * @throws EntityNotFoundException if a group has not been found
     */
    @GetMapping("/{group:\\d+}/delete")
    public RedirectView deleteGroup(@PathVariable Group group) throws EntityNotFoundException {
        if (group == null) {
            log.error("GROUPS CONTROLLER deleteGroup(): group not found");
            throw new EntityNotFoundException("error.group.notFound");
        }
        groupRepository.delete(group);
        return new RedirectView("/groups");
    }

    /**
     * Displaying a form to edit a given group.
     *
     * @param group an entity to be edited
     * @return model and view
     * @throws EntityNotFoundException if a group has not been found
     */
    @GetMapping("/{group:\\d+}/edit")
    public ModelAndView showEditGroupForm(@PathVariable Group group) throws EntityNotFoundException {
        if (group == null) {
            log.error("GROUPS CONTROLLER showEditGroupForm(): group not found");
            throw new EntityNotFoundException("error.group.notFound");
        }
        GroupDto groupDto = new GroupDto();
        groupDto.setId(group.getId());
        groupDto.setName(group.getName());
        groupDto.setFacultyName(group.getFacultyName());

        ModelMap model = new ModelMap();
        model.addAttribute(group);
        model.addAttribute(groupDto);
        return new ModelAndView("groups/edit", model);
    }

    /**
     * Checking that the group with given id exist and save it.
     * Otherwise throw the exception if one does not exist.
     *
     * @param groupDto the changed group
     * @param result   verification errors if exist
     * @param group    an entity fetched by id from DB if one exists
     * @return model and view name
     * @throws EntityNotFoundException if a group has not been found
     */
    @PostMapping("/{group:\\d+}/edit")
    public ModelAndView saveChangedGroupIfValid(@Valid @ModelAttribute GroupDto groupDto, BindingResult result, @PathVariable Group group)
            throws EntityNotFoundException {
        if (group == null) {
            log.error("GROUPS CONTROLLER saveChangedGroupIfValid(): group not found");
            throw new EntityNotFoundException("error.group.notFound");
        }

        if (result.hasErrors()) {
            groupDto.setId(group.getId());
            ModelMap model = new ModelMap();
            model.addAttribute(groupDto);
            model.addAttribute(group);
            return new ModelAndView("groups/edit", model, HttpStatus.BAD_REQUEST);
        }

        group.setName(groupDto.getName());
        group.setFacultyName(groupDto.getFacultyName());
        groupRepository.save(group);

        return new ModelAndView("redirect:/groups/" + group.getId());
    }
}

package dev.chernykh.studentmanagement.groups;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Controller to manage student groups.
 */
@AllArgsConstructor
@Controller
@RequestMapping("/groups")
public class GroupsController {
    private GroupService groupService;

    /**
     * Displaying a list of groups with pagination.
     *
     * @param pageable a page implementation instance provided by spring
     * @return model and view name
     */
    @GetMapping
    public ModelAndView getGroups(@PageableDefault Pageable pageable) {
        ModelMap model = new ModelMap();
        model.addAttribute(groupService.getAll(pageable));
        return new ModelAndView("groups/list", model);
    }

    /**
     * Displaying a form to input group's details.
     *
     * @return model and view name
     */
    @GetMapping("create")
    public ModelAndView createGroupForm() {
        ModelMap model = new ModelMap();
        model.addAttribute(new GroupDto());
        return new ModelAndView("groups/create", model);
    }

    /**
     * Validate and save a group.
     *
     * @param groupDto dto object to be validate and converted to student
     * @param result   validation errors if exist
     * @return model and view name
     */
    @PostMapping("create")
    public ModelAndView createGroup(@Valid @ModelAttribute GroupDto groupDto, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("groups/create", HttpStatus.BAD_REQUEST);
        }
        groupService.create(groupDto);
        return new ModelAndView("redirect:/groups");
    }

    /**
     * Displaying details of a group with given id.
     *
     * @param id group id
     * @return model and view
     */
    @GetMapping("{id}")
    public ModelAndView viewGroup(@PathVariable long id) {
        Group group = groupService.getOne(id);
        ModelMap model = new ModelMap();
        model.addAttribute(group);
        return new ModelAndView("groups/viewOne", model);
    }

    /**
     * Deleting a group with given id.
     *
     * @param id group id
     * @return model and view
     */
    @GetMapping("{id}/delete")
    public ModelAndView deleteGroup(@PathVariable long id) {
        groupService.delete(id);
        return new ModelAndView("redirect:/groups");
    }

    /**
     * Displaying a form to edit a group with a given id.
     *
     * @param id group id
     * @return model and view
     */
    @GetMapping("{id}/edit")
    public ModelAndView editGroupForm(@PathVariable long id) {
        Group group = groupService.getOne(id);
        ModelMap model = new ModelMap();
        model.addAttribute(group);
        model.addAttribute(new GroupDto(group));
        return new ModelAndView("groups/edit", model);
    }

    /**
     * Update the group with given id.
     *
     * @param groupDto the changed group
     * @param result   validation errors if exist
     * @return model and view name
     */
    @PostMapping("{id}/edit")
    public ModelAndView editGroup(@Valid @ModelAttribute GroupDto groupDto, BindingResult result) {

        if (result.hasErrors()) {
            return new ModelAndView("groups/edit", HttpStatus.BAD_REQUEST);
        }
        groupService.update(groupDto);
        return new ModelAndView("redirect:/groups/" + groupDto.getId());
    }
}

package dev.chernykh.studentmanagement.groups;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StudentGroupControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GroupRepository repository;

    /**
     * Displaying a list of groups.
     */
    @Test
    public void shouldReturnGroupModelAndViewName() throws Exception {
        mockMvc.perform(get("/groups"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("groups/list"))
                .andExpect(model().attributeExists("pageImpl"));
    }

    /**
     * Displaying an existing group.
     */
    @Test
    public void shouldReturnViewGroupPageIfOneExists() throws Exception {
        mockMvc.perform(get("/groups/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("groups/viewOne"))
                .andExpect(model().attributeExists("group"));
    }

    /**
     * Displaying error page if a group with given id does not exist.
     */
    @Test
    public void shouldReturnErrorPageWhenRequestedGroupDoesNotExist() throws Exception {
        mockMvc.perform(get("/groups/3"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    /**
     * Displaying a page with a form to create a new group.
     */
    @Test
    public void shouldReturnGroupCreationPage() throws Exception {
        mockMvc.perform(get("/groups/create"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("groups/create"))
                .andExpect(model().attributeExists("groupDto"));
    }

    /**
     * Checking user-provided group data save the group and redirect to a page with a list of groups.
     */
    @Test
    public void shouldSaveValidGroupAndRedirectToGroupsPage() throws Exception {
        mockMvc.perform(post("/groups/create")
                .param("name", "IVT")
                .param("number", "21")
                .param("facultyName", "FIT")
        )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/groups"));
    }

    /**
     * If user-provided group data is invalid then return a page with a form.
     */
    @Test
    public void shouldRedirectToCreationPageIfGroupInvalid() throws Exception {
        mockMvc.perform(post("/groups/create"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(view().name("groups/create"))
                .andExpect(model().attributeExists("groupDto"));
    }

    /**
     * Displaying an error page when the group to be deleted doesn't exist.
     */
    @Test
    public void shouldReturnErrorPageWhenDeletingNotExistingGroup() throws Exception {
        mockMvc.perform(get("/groups/3/delete"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    /**
     * Redirecting to a page with a list of groups if given group has been deleted successfully.
     */
    @Test
    public void shouldDeleteGivenGroupAndRedirectToPageWithListOfGroups() throws Exception {
        mockMvc.perform(get("/groups/2/delete"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/groups"));

        assertFalse(repository.exists(3L));
    }

    /**
     * Displaying a page with a form to edit a group.
     */
    @Test
    public void shouldReturnPageToEditGroup() throws Exception {
        mockMvc.perform(get("/groups/2/edit"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("groups/edit"))
                .andExpect(model().attributeExists("groupDto"));
    }

    /**
     * Save the updated group and redirect to a page to view the updated group.
     */
    @Test
    public void shouldSaveUpdatedGroupAndRedirectToViewGroupPage() throws Exception {
        mockMvc.perform(
                post("/groups/2/edit")
                        .param("id", "2")
                        .param("name", "IVT")
                        .param("number", "31")
                        .param("facultyName", "FIT")
        )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/groups/2"));
    }

    /**
     * Displaying the form to edit the group if user-provided data is invalid.
     */
    @Test
    public void shouldReturnFormPageIfInvalidDataOfGroupProvided() throws Exception {
        mockMvc.perform(post("/groups/2/edit"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(view().name("groups/edit"))
                .andExpect(model().attributeExists("groupDto"));
    }
}

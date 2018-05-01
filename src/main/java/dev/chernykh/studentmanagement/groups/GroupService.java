package dev.chernykh.studentmanagement.groups;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * An interface to perform CRUD operations over student groups.
 */
public interface GroupService {
    /**
     * Get a page of group list.
     *
     * @param pageable an instance specifying a number and size of page
     * @return a page of groups
     */
    Page<Group> getAll(Pageable pageable);

    /**
     * Get group by id.
     *
     * @param id group id
     * @return group if one exists
     */
    Group getOne(long id);

    /**
     * Create a new group.
     *
     * @param groupDto dto object to be converted to group and then saved
     */
    void create(GroupDto groupDto);

    /**
     * Update an existing group.
     *
     * @param groupDto dto object to be converted to group and then saved
     */
    void update(GroupDto groupDto);

    /**
     * Delete group by id.
     *
     * @param id group id
     */
    void delete(long id);
}

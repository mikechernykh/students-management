package dev.chernykh.studentmanagement.groups;

import dev.chernykh.studentmanagement.errors.DuplicateGroupException;
import dev.chernykh.studentmanagement.errors.EntityNotFoundException;
import dev.chernykh.studentmanagement.errors.GroupNotEmptyException;
import dev.chernykh.studentmanagement.students.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Implementation of the GroupService interface.
 */
@AllArgsConstructor
@Service
@Slf4j
public class GroupServiceImpl implements GroupService {
    private GroupRepository groupRepository;
    private StudentRepository studentRepository;

    @Override
    public Page<Group> getAll(@NonNull Pageable pageable) {
        log.info("GROUP SERVICE get a list of groups");
        return groupRepository.findAll(pageable);
    }

    @Override
    public Group getOne(long id) {
        log.info("GROUP SERVICE get group with id = " + id);
        Group group = groupRepository.findOne(id);
        if (group == null) {
            throw new EntityNotFoundException("Group with id = " + id + " not found");
        }
        return group;
    }

    @Override
    public void create(@NonNull GroupDto groupDto) {
        log.info("GROUP SERVICE create a new group");
        Group groupByName = groupRepository.findByNameLike(groupDto.getName());
        if (groupByName != null) {
            String message = "Group " + groupDto.getName() + " already exists";
            log.error(message);
            throw new DuplicateGroupException(message);
        }
        groupRepository.save(groupDto.toGroup());
    }

    @Override
    public void update(@NonNull GroupDto groupDto) {
        log.info("GROUP SERVICE update a group with id = " + groupDto.getId());
        if (!groupRepository.exists(groupDto.getId())) {
            throw new EntityNotFoundException("Group with id=" + groupDto.getId() + " not found");
        }
        if (groupRepository.existsByNameLike(groupDto.getName())) {
            throw new DuplicateGroupException("Group <strong>" + groupDto.getName() + "</strong> already exists");
        }
        groupRepository.save(groupDto.toGroup());
    }

    @Override
    public void delete(long id) {
        log.info("GROUP SERVICE delete a group with id = " + id);
        Group group = groupRepository.findOne(id);
        if (group == null) {
            String message = "Group with id=" + id + " not found";
            log.error(message);
            throw new EntityNotFoundException(message);
        }

        if (studentRepository.findByGroup(group).isEmpty()) {
            groupRepository.delete(group);
        } else {
            String message = "Group " + group.getName() + " is not empty";
            log.error(message);
            throw new GroupNotEmptyException(message);
        }
    }
}

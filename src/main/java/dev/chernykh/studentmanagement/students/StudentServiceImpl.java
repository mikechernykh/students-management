package dev.chernykh.studentmanagement.students;

import dev.chernykh.studentmanagement.errors.EntityNotFoundException;
import dev.chernykh.studentmanagement.groups.Group;
import dev.chernykh.studentmanagement.groups.GroupRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Implementation of the StudentService interface.
 */
@AllArgsConstructor
@Service
@Slf4j
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;
    private GroupRepository groupRepository;

    @Override
    public Page<Student> getAll(@NonNull Pageable pageable) {
        log.info("STUDENT SERVICE get a list of students");
        return studentRepository.findAll(pageable);
    }

    @Override
    public Student getOne(long id) {
        log.info("STUDENT SERVICE get student with id = " + id);
        Student student = studentRepository.findOne(id);
        if (student == null) {
            throw new EntityNotFoundException("Student with id = " + id + " not found");
        }
        return student;
    }

    @Override
    public void create(@NonNull StudentDto studentDto) {
        log.info("STUDENT SERVICE create a new student");
        Group group = groupRepository.findByNameLike(studentDto.getGroupName());
        if (group == null) {
            throw new EntityNotFoundException("Group with name = " + studentDto.getGroupName() + " not found");
        }
        Student student = studentDto.toStudent();
        student.setGroup(group);
        studentRepository.save(student);
    }

    @Override
    public void update(@NonNull StudentDto studentDto) {
        log.info("STUDENT SERVICE update a student with id = " + studentDto.getId());
        if (!studentRepository.exists(studentDto.getId())) {
            throw new EntityNotFoundException("Student with id=" + studentDto.getId() + " not found");
        }
        studentRepository.save(studentDto.toStudent());
    }

    @Override
    public void delete(long id) {
        log.info("STUDENT SERVICE delete a student with id = " + id);
        if (studentRepository.exists(id)) {
            studentRepository.delete(id);
        } else {
            String message = "Student with id = " + id + " not found";
            log.error(message);
            throw new EntityNotFoundException(message);
        }
    }
}

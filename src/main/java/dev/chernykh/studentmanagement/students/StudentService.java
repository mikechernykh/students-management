package dev.chernykh.studentmanagement.students;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * An interface to perform CRUD operations over students.
 */
public interface StudentService {
    /**
     * Get a page of student list.
     *
     * @param pageable an instance specifying a number and size of page
     * @return a page of students
     */
    Page<Student> getAll(Pageable pageable);

    /**
     * Get student by id.
     *
     * @param id student id
     * @return student if one exists
     */
    Student getOne(long id);

    /**
     * Create a new student.
     *
     * @param studentDto dto object to be converted to student and then saved
     */
    void create(StudentDto studentDto);

    /**
     * Update an existing student.
     *
     * @param studentDto dto object to be converted to student and then saved
     */
    void update(StudentDto studentDto);

    /**
     * Delete student by id.
     *
     * @param id student id
     */
    void delete(long id);
}

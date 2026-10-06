package in.strikes.crudSpringbootDemo.Service;

import in.strikes.crudSpringbootDemo.Entity.Student;
import in.strikes.crudSpringbootDemo.Repository.StudentRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private static StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public static Student createStudent(Student studentReq) {

        studentReq.setDeleted(false);
        Student studentResp = studentRepository.save(studentReq);
        return studentResp;
    }

    public static Student getStudent(Long id) {
        Optional<Student> studentResp = studentRepository.findByIdAndDeletedIsFalse(id);

        if (studentResp.isPresent()) {
            return studentResp.get();
        }
        return null;
    }

    public static List<Student> getAllStudent() {
        List<Student> studentList = studentRepository.findByDeletedIsFalse();

        return studentList;
    }

    public static Student updateStudent(Long id, Student studentReq) {
        Optional<Student> existingStudent = studentRepository.findByIdAndDeletedIsFalse(id);

        if (existingStudent.isEmpty()) {
            return null;
        }

        Student studentToSave = existingStudent.get();
        studentToSave.setId(studentReq.getId());
        studentToSave.setName(studentReq.getName());
        studentToSave.setEmail(studentReq.getEmail());
        studentToSave.setRoll_no(studentReq.getRoll_no());
        studentToSave.setAge(studentReq.getAge());
        studentToSave.setDeleted(false);


        return studentRepository.save(studentToSave);

    }

    public static Boolean deleteStudent(Long id) {
        Boolean isStudent = studentRepository.existsById(id);

        if (!isStudent) return false;

        studentRepository.deleteById(id);
        return true;
    }

    public static Boolean deleteStudentSoftly(Long id) {

        Optional<Student> existingStudent = studentRepository.findByIdAndDeletedIsFalse(id);

        if (existingStudent.isEmpty()){
            return false;
        }

        Student studentToSave = existingStudent.get();
        studentToSave.setDeleted(true);
        studentRepository.save(studentToSave);
        return true;
    }
}

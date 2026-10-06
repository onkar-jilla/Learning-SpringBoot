package in.strikes.crudSpringbootDemo.Controller;


import ch.qos.logback.classic.boolex.StubEventEvaluator;
import in.strikes.crudSpringbootDemo.Entity.Student;
import in.strikes.crudSpringbootDemo.Service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    // for creating new student record
    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){

        Student createdStudent = StudentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);

    }

    // for getting student record from id
    @GetMapping("/get/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id){

        Student studentResp = StudentService.getStudent(id);

        if(studentResp == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(studentResp);
    }

    // for getting all student records from id
    @GetMapping("/getAll")
    public ResponseEntity <List<Student>> getAllStudent(){

        List<Student> studentList = StudentService.getAllStudent();

        if(studentList.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(studentList);    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id,@RequestBody Student studentReq){


        Student studentResp = StudentService.updateStudent(id,studentReq);

        if(studentResp == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(studentResp);

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){

        Boolean isDeleted = StudentService.deleteStudent(id);

        if(!isDeleted){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("record deleted successfully....");
    }

    @PatchMapping("/delete-soft/{id}")
    public ResponseEntity<String> deleteStudentSoftly(@PathVariable Long id){

        Boolean isDeleted = StudentService.deleteStudentSoftly(id);

        if(!isDeleted){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("record deleted successfully....");
    }

}


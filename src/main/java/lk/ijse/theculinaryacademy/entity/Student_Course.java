package lk.ijse.theculinaryacademy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "student_course")
public class Student_Course {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "student_course_id")
        private Long studentCourseId;

        @Column(name = "registration_date", nullable = false)
        private String registrationDate;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "student_id", nullable = false)
        private Student student;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "course_id", nullable = false)
        private Course course;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "pay_id")
        private Payment payment;
}


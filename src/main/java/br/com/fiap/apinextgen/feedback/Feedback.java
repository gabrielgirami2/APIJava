package br.com.fiap.apinextgen.feedback;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "feedbacks")
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String feeling;
    private LocalDate feedbackDate;
    private String company;

}

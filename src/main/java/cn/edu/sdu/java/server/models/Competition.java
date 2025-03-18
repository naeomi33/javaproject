package cn.edu.sdu.java.server.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(	name = "competition",
        uniqueConstraints = {
        })
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "名称不能为空")
    @Size(max = 50)
    @JoinColumn(name = "competition_name")
    private String name;

    @Size(max = 20)
    @JoinColumn(name = "competition_time")
    private String time;

    @Size(max = 50)
    @JoinColumn(name = "competition_place")
    private String place;

    @Size(max = 200)
    @JoinColumn(name = "competition_description")
    private String description;
}

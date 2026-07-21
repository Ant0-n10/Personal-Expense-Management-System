package Ant0_n10.financas.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_Category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Category(String name){
        this.name = name;
    }

    @PrePersist
    @PreUpdate
    private void formatName() {
        if (this.name != null) {
            this.name = this.name.toUpperCase();
        }
    }

}

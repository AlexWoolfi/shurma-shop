package com.shop.shurma.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name="sendshaurma")
@NoArgsConstructor
@AllArgsConstructor
public class Shaurma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private double price;
    @Column
    private String linkimg;
    @Column(columnDefinition = "text")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "shaurma")
    private List<Image> images = new ArrayList<>(  );
    private Long previewImgId;
    private LocalDateTime dateCreated;

    @PrePersist
    private void init() {
        dateCreated=LocalDateTime.now();
    }

    public void imageToShaurma(Image image) {
        image.setShaurma(this);
        images.add(image);
    }
}

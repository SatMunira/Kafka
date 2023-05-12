    package com.bezkoder.spring.security.postgresql.models;

    import lombok.Data;

    import javax.persistence.*;
    import java.util.Date;
    import java.util.List;

    @Entity
    @Data
    @Table(name="editions")
    public class Edition {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id", nullable = false)
        private Long id;

        private String image;
        private Integer manufactureDate;
        @Column(name = "name", length = 15000)
        private String description;

        @ManyToOne(fetch = FetchType.LAZY)
        private Book book;

        @ManyToMany(cascade = CascadeType.MERGE)
        private List<Publisher> publishers;

        public String getImageUrl() {
            return "/images/imagePreview/" + this.getId() + "/" + this.getImage();
        }












    }

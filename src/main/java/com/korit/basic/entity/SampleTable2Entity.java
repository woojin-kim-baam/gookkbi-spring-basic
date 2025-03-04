package com.korit.basic.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sample_table_2")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SampleTable2Entity {
    
    @Id
    // @Column 이름이 같다면 생략 가능
    // @GeneatedValue :
    // - PRIMARY KEY에 값을 자동 생성하는 전략을 지정해주는 어노테이션
    // - strategy 속성 : 자동 생성 전략을 지정
    // - GenerationType.Auto : JPA가 적절한 생성 전략 지정
    // - GenerationType.Identity : auto increment 전략 선택
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer aiPk;

    private String sampleColumn;

}

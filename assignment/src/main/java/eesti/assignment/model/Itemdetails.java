package eesti.assignment.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Itemdetails {
	@Id
	private Integer id;
    @Column
    private String name;
    @Column
    private double price;
    @Column
    private String type;
    @Column
    private int quantity;
    
}

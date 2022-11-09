package eesti.assignment.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Table("items")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Itemdetails implements	Persistable<Integer> {
	public Itemdetails(Integer id, String name, double price, String type, int quantity, boolean entry) {
		super();
		
		this.id = id;
		this.name = name;
		this.price = price;
		this.type = type;
		this.quantity = quantity;
		this.entry = entry;
	}

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
    @Transient
    private int orderQuntity;
    @Column
    private boolean entry;
    @Transient
    private int quantityInsert;
    @Transient
    private boolean newProduct;
    @Transient
    private boolean imgDisabled;

    @Override
    @Transient
    public boolean isNew() {
        return this.newProduct || id == null;
    }

    public Itemdetails setAsNew(){
        this.newProduct = true;
        return this;
    }
    
    

}

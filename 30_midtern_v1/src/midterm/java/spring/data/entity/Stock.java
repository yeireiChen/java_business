package midterm.java.spring.data.entity;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sun.org.apache.regexp.internal.REUtil;

@Entity
@Table(name = "[STOCK]")
public class Stock  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "STOCKNAME")
	private String stockname;
	
	@Column(name = "STOCKPRICE")
	private int stockprice;
	
	
	public int getId(){
		return id;
	}
	
	public String getStockName(){
		return stockname;
	}
	
	public void setStockName(String n){
		this.stockname = n;
	}
	
	public int getStockPrice(){
		return stockprice;
	}
	
	public void setStockName(int p){
		this.stockprice = p;
	}
	
	
}


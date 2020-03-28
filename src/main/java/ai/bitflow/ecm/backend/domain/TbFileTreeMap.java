package ai.bitflow.ecm.backend.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
@Entity
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class TbFileTreeMap {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(length = 100, nullable = false)
	private String title;
	@Column(columnDefinition = "text", nullable = false)
	private String content;
	@Column(length = 500, nullable = false)
	private String path;
	@Column(length = 10, nullable = false)
	private String ext;
	@Column(columnDefinition="boolean default false")
	private boolean isDir;
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<TbFileTreeMap> child;
    
}

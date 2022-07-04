package com.cc.test301.common.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public class AppEntity implements Serializable {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private UUID id;

	@Column(length = 30)
	@Size(max = 30)
	private String name;

	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime created;

	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime lastModified;

	@PrePersist
	protected void init() {
		created = LocalDateTime.now();
		lastModified = created;
	}

	@PreUpdate
	protected void update() {
		lastModified = LocalDateTime.now();
	}
}

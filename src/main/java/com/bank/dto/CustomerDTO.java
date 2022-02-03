package com.bank.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
	
	@NotNull
	@Min(1)
	private Integer custId;

	@NotNull
	@Size(min=4, max = 225)
	@NotEmpty
	private String address;

	@NotNull
	@Size(min=4, max = 225)
	@Email
	@NotEmpty
	private String email;

	@NotNull
	private Boolean enable;

	@NotNull
	@Size(min=4, max = 225)
	@NotEmpty
	private String name;

	@NotNull
	@Size(min=4, max = 225)
	@NotEmpty
	private String phone;

	private String token;
	
	@NotNull
	@Min(0)
	private Integer dotyId;

}

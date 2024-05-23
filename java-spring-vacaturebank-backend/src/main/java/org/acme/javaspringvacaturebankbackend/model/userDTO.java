package org.acme.javaspringvacaturebankbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class userDTO {
    private String userEmail;
    private String userName;
    private String userRole;
    private Integer employersEmployerId;
}

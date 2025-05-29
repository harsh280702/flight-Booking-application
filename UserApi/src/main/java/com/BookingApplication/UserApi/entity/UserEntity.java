package com.BookingApplication.UserApi.entity;

import brave.internal.Nullable;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@Entity
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
   private String lastName;
    private String userName;
    @Column(unique = true,nullable = false)
    private String email;

    private String password;
    private String role;

    private  String keycloakId;

  //  @Column(unique = true)
//    private String passPortId;
//    @Column(unique = true)
//    private String phone;
//    private String address;
//    private String city;
//    private String state;
//    private String zip;
//    private Long bookingId;
//    private Long flightId;





}

///**
// * 
// */
//package com.simiyu.controller;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @author enock
// *
// */
//@RestController
//public class UserResource {
//
//	@PreAuthorize("hasRole('ADMIN')")
//	@GetMapping(value="/getdata")
//	public String adminPing(){
//        return "Only Admins Can Read This";
//    }
//	
//	
//	@PreAuthorize("hasRole('USER')")
//	@GetMapping(value="/getdata2")
//	public String userPing(){
//        return "Any User Can Read This";
//    }
//}

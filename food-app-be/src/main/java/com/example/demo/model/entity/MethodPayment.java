package com.example.demo.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "method_payment")
public class MethodPayment {
    @Id
    @Column(name = "method_payment_id")
    private Integer methodPaymentId;

    @Column(name = "method_payment_name")
    private String methodPaymentName;

    public Integer getMethodPaymentId() {
        return methodPaymentId;
    }

    public void setMethodPaymentId(Integer methodPaymentId) {
        this.methodPaymentId = methodPaymentId;
    }

    public String getMethodPaymentName() {
        return methodPaymentName;
    }

    public void setMethodPaymentName(String methodPaymentName) {
        this.methodPaymentName = methodPaymentName;
    }
}

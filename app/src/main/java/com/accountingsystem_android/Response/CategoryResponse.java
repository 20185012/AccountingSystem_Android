package com.accountingsystem_android.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse implements Serializable {
    private int categoryID;
    private String categoryName;
    private List<UserResponse> responsibleUsers = new ArrayList<>();
    private List<SubCategoryResponse> subCategories = new ArrayList<>();
    private int parentCategoryID;
    private List<ReceivableResponse> receivables = new ArrayList<>();
    private List<PaymentResponse> payments = new ArrayList<>();

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<UserResponse> getResponsibleUsers() {
        return responsibleUsers;
    }

    public void setResponsibleUsers(List<UserResponse> responsibleUsers) {
        this.responsibleUsers = responsibleUsers;
    }

    public int getParentCategoryID() {
        return parentCategoryID;
    }

    public void setParentCategoryID(int parentCategoryID) {
        this.parentCategoryID = parentCategoryID;
    }

    public List<SubCategoryResponse> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategoryResponse> subCategories) {
        this.subCategories = subCategories;
    }

    public List<ReceivableResponse> getReceivables() {
        return receivables;
    }

    public void setReceivables(List<ReceivableResponse> receivables) {
        this.receivables = receivables;
    }

    public List<PaymentResponse> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentResponse> payments) {
        this.payments = payments;
    }
}

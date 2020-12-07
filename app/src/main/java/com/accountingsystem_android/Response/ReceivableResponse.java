package com.accountingsystem_android.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceivableResponse implements Serializable {
    private int receivableId;
    private float receivableSum;
    private String receivableDate;
    private int categoryID;

    public int getReceivableId() {
        return receivableId;
    }

    public void setReceivableId(int receivableId) {
        this.receivableId = receivableId;
    }

    public float getReceivableSum() {
        return receivableSum;
    }

    public void setReceivableSum(float receivableSum) {
        this.receivableSum = receivableSum;
    }

    public String getReceivableDate() {
        return receivableDate;
    }

    public void setReceivableDate(String receivableDate) {
        this.receivableDate = receivableDate;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }
}

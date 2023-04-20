package com.warehouse_manager;

public class Unit {
    public Integer unitID;
    public String unitName;
    public String unitDepartment;
    public Integer unitCount;
   

    public Unit(Integer ID, String Name, String Department, Integer Count){
        this.unitID = ID;
        this.unitName = Name;
        this.unitDepartment = Department;
        this.unitCount = Count;
    }


    public Integer getUnitID() {
        return unitID;
    }


    public String getUnitName() {
        return unitName;
    }


    public String getUnitDepartment() {
        return unitDepartment;
    }


    public Integer getUnitCount() {
        return unitCount;
    }


    public void setUnitID(Integer unitID) {
        this.unitID = unitID;
    }


    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }


    public void setUnitDepartment(String unitDepartment) {
        this.unitDepartment = unitDepartment;
    }


    public void setUnitCount(Integer unitCount) {
        this.unitCount = unitCount;
    }
}

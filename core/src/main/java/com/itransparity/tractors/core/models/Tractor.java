package com.itransparity.tractors.core.models;

public class Tractor {
    private String modelName;
    private String shortDescription;
    private String fullDescription;
    private String hp;
    private String engineType;
    private String weight;
    private String fuelCapacity;
    private String image;
    private String detailPagePath;

    public Tractor() {}

    // Getters & Setters
    public String getModelName() { return modelName; }
    public void setModelName(String modelName) { this.modelName = modelName; }

    public String getShortDescription() { return shortDescription; }
    public void setShortDescription(String shortDescription) { this.shortDescription = shortDescription; }

    public String getFullDescription() { return fullDescription; }
    public void setFullDescription(String fullDescription) { this.fullDescription = fullDescription; }

    public String getHp() { return hp; }
    public void setHp(String hp) { this.hp = hp; }

    public String getEngineType() { return engineType; }
    public void setEngineType(String engineType) { this.engineType = engineType; }

    public String getWeight() { return weight; }
    public void setWeight(String weight) { this.weight = weight; }

    public String getFuelCapacity() { return fuelCapacity; }
    public void setFuelCapacity(String fuelCapacity) { this.fuelCapacity = fuelCapacity; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public String getDetailPagePath() { return detailPagePath; }
    public void setDetailPagePath(String detailPagePath) { this.detailPagePath = detailPagePath; }
}
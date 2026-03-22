package com.SimonGithyb.delivery.application.mapper;

import com.SimonGithyb.delivery.application.dto.DimensionsResponse;
import com.SimonGithyb.delivery.application.dto.ParcelResponse;
import com.SimonGithyb.delivery.application.dto.WeightResponse;
import com.SimonGithyb.delivery.domain.model.shipment.Parcel;

public class ParcelMapper {

    public static ParcelResponse toResponse(Parcel parcel) {

        return new ParcelResponse(
                parcel.getId(),
                new WeightResponse(
                        parcel.getWeight().getKilograms(),
                        "kg"
                ),
                new DimensionsResponse(
                        parcel.getDimensions().getLength(),
                        parcel.getDimensions().getWidth(),
                        parcel.getDimensions().getHeight(),
                        "cm"
                ),
                parcel.getDescription()
        );
    }
}

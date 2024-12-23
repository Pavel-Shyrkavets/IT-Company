/*
 * %W% %E% Pavel Shyrkavets
 *
 * Copyright (c) 2011-2024 Solvd, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Solvd,
 * Inc. ("Confidential Information.") You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Solvd.
 *
 * SOLVD MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. SOLVD SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 */
package com.solvd.itcompany;

import com.solvd.itcompany.enums.ABOBloodGroup;
import com.solvd.itcompany.enums.RhTypeBloodFactor;

import java.util.Objects;

public class BloodType {
    private final static int MASK = 200;

    private ABOBloodGroup bloodGroup = null;
    private RhTypeBloodFactor bloodFactor = null;

    public BloodType() {}

    public BloodType(ABOBloodGroup bloodGroup, RhTypeBloodFactor bloodFactor) {
        this.bloodGroup = bloodGroup;
        this.bloodFactor = bloodFactor;
    }

    public void setBloodGroup(ABOBloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public ABOBloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodFactor(RhTypeBloodFactor bloodFactor) {
        this.bloodFactor = bloodFactor;
    }

    public RhTypeBloodFactor getBloodFactor() {
        return bloodFactor;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        if (hashCode() != object.hashCode()) {
            return false;
        }

        BloodType bloodType = (BloodType) object;
        return getBloodGroup() == bloodType.getBloodGroup()
                && getBloodFactor() == bloodType.getBloodFactor();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBloodGroup(), getBloodFactor(), MASK);
    }

    @Override
    public String toString() {
        return "BloodType{"
                + "bloodGroup=" + bloodGroup
                + ", bloodFactor=" + bloodFactor
                + '}';
    }
}

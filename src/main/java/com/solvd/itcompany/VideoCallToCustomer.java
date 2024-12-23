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

import com.solvd.itcompany.exceptions.DateTimeException;
import com.solvd.itcompany.exceptions.TooManyPersonsException;
import com.solvd.itcompany.exceptions.ZeroOrTooManyCharactersException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class VideoCallToCustomer extends Meeting {
    private final static int MASK = 21;

    private Customer customer = null;

    public VideoCallToCustomer() {}

    public VideoCallToCustomer(String name, LocalDateTime startDateTime,
                               LocalDateTime endDateTime, List<Person> participants, Customer customer)
                               throws ZeroOrTooManyCharactersException, DateTimeException,
                               TooManyPersonsException {
        super(name, startDateTime, endDateTime, participants);
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

        VideoCallToCustomer that = (VideoCallToCustomer) object;
        return Objects.equals(getName(), that.getName())
                && getStartDateTime().isEqual(that.getStartDateTime())
                && Objects.equals(getParticipants(), that.getParticipants())
                && Objects.equals(getCustomer(), that.getCustomer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getStartDateTime(), getParticipants(),
                            getCustomer(), MASK);
    }

    @Override
    public String toString() {
        return "VideoCallToCustomer{"
                + "customer=" + getCustomer()
                + ", VideoCallToCustomerName='" + getName() + '\''
                + ", startDateTime='" + getStartDateTime() + '\''
                + ", endDateTime='" + getEndDateTime() + '\''
                + ", participants=" + getParticipants()
                + '}';
    }
}

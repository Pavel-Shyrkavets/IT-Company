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
import com.solvd.itcompany.interfaces.IParticipate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.List;

public abstract class Meeting implements IParticipate {
    protected final static int MAX_MEETING_NAME_LENGTH = 255;
    protected final static int MAX_NUMBER_OF_PARTICIPANTS = 100;
    private final static String MEETING_NAME_MESSAGE =
            "The meeting name length should not be equal to 0 "
            + "or exceed " + MAX_MEETING_NAME_LENGTH + " characters.";
    private final static String START_MESSAGE =
            "The date and time of the meeting beginning should not be "
            + "earlier than the current date and time.";
    private final static String END_MESSAGE =
            "The date and time of the meeting end should not be "
            + "earlier than or equal to the meeting beginning.";
    private final static String PARTICIPANTS_MESSAGE =
            "The number of the meeting participants should not exceed "
            + MAX_NUMBER_OF_PARTICIPANTS + " persons.";
    private final static Logger LOGGER = LogManager.getLogger(Meeting.class);

    protected String name = "";
    protected LocalDateTime startDateTime = null;
    protected LocalDateTime endDateTime = null;
    protected List<Person> participants = null;

    public Meeting() {}

    public Meeting(String name, LocalDateTime startDateTime,
                   LocalDateTime endDateTime, List<Person> participants)
                   throws ZeroOrTooManyCharactersException, DateTimeException,
                   TooManyPersonsException {
        if (name.isEmpty() || name.length() > MAX_MEETING_NAME_LENGTH) {
            LOGGER.error(MEETING_NAME_MESSAGE);
            throw new ZeroOrTooManyCharactersException(MEETING_NAME_MESSAGE);
        }

        if (startDateTime.isBefore(LocalDateTime.now())) {
            LOGGER.error(START_MESSAGE);
            throw new DateTimeException(START_MESSAGE);
        }

        if (!endDateTime.isAfter(getStartDateTime())) {
            LOGGER.error(END_MESSAGE);
            throw new DateTimeException(END_MESSAGE);
        }

        if (participants.size() > MAX_NUMBER_OF_PARTICIPANTS) {
            LOGGER.error(PARTICIPANTS_MESSAGE);
            throw new TooManyPersonsException(PARTICIPANTS_MESSAGE);
        }

        this.name = name;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.participants = participants;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws ZeroOrTooManyCharactersException {
        if (name.isEmpty() || name.length() > MAX_MEETING_NAME_LENGTH) {
            LOGGER.error(MEETING_NAME_MESSAGE);
            throw new ZeroOrTooManyCharactersException(MEETING_NAME_MESSAGE);
        }

        this.name = name;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime)
            throws DateTimeException {
        if (startDateTime.isBefore(LocalDateTime.now())) {
            LOGGER.error(START_MESSAGE);
            throw new DateTimeException(START_MESSAGE);
        }

        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) throws DateTimeException {
        if (!endDateTime.isAfter(getStartDateTime())) {
            LOGGER.error(END_MESSAGE);
            throw new DateTimeException(END_MESSAGE);
        }

        this.endDateTime = endDateTime;
    }

    public List<Person> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Person> participants)
            throws TooManyPersonsException {
        if (participants.size() > MAX_NUMBER_OF_PARTICIPANTS) {
            LOGGER.error(PARTICIPANTS_MESSAGE);
            throw new TooManyPersonsException(PARTICIPANTS_MESSAGE);
        }

        this.participants = participants;
    }
}

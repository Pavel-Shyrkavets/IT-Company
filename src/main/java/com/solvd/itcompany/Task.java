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
import com.solvd.itcompany.exceptions.ZeroOrTooManyCharactersException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.Objects;

public class Task {
    private final static int MASK = 1;
    private final static int MAX_TASK_NAME_LENGTH = 255;
    private final static String TASK_NAME_MESSAGE =
            "The task name length should not be equal to 0 "
            + "or exceed " + MAX_TASK_NAME_LENGTH + " characters.";
    private final static String DEADLINE_MESSAGE =
            "The deadline should not be earlier than "
            + "or equal to the current date and time.";
    private final static Logger LOGGER = LogManager.getLogger(Task.class);

    private String name = "";
    private LocalDateTime deadline = null;

    public Task() {}

    public Task(String name, LocalDateTime deadline)
                throws ZeroOrTooManyCharactersException, DateTimeException {
        if (name.isEmpty() || name.length() > MAX_TASK_NAME_LENGTH) {
            LOGGER.error(TASK_NAME_MESSAGE);
            throw new ZeroOrTooManyCharactersException(TASK_NAME_MESSAGE);
        }

        if (!deadline.isAfter(LocalDateTime.now())) {
            LOGGER.error(DEADLINE_MESSAGE);
            throw new DateTimeException(DEADLINE_MESSAGE);
        }

        this.name = name;
        this.deadline = deadline;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws ZeroOrTooManyCharactersException {
        if (name.isEmpty() || name.length() > MAX_TASK_NAME_LENGTH) {
            LOGGER.error(TASK_NAME_MESSAGE);
            throw new ZeroOrTooManyCharactersException(TASK_NAME_MESSAGE);
        }

        this.name = name;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) throws DateTimeException {
        if (!deadline.isAfter(LocalDateTime.now())) {
            LOGGER.error(DEADLINE_MESSAGE);
            throw new DateTimeException(DEADLINE_MESSAGE);
        }

        this.deadline = deadline;
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

        Task task = (Task) object;
        return Objects.equals(getName(), task.getName())
                && getDeadline().isEqual(task.getDeadline());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDeadline(), MASK);
    }

    @Override
    public String toString() {
        return "Task{"
                + "name='" + getName() + '\''
                + ", deadline='" + getDeadline() + '\''
                + '}';
    }
}

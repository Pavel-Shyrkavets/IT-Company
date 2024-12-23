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

import com.solvd.itcompany.exceptions.TooManyPersonsException;
import com.solvd.itcompany.exceptions.ZeroOrTooManyCharactersException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Objects;

public class Team {
    private final static int MASK = 30;
    private final static int MAX_TEAM_NAME_LENGTH = 100;
    private final static int MAX_NUMBER_OF_TEAM_MEMBERS = 30;
    private final static String TEAM_NAME_MESSAGE =
            "The team name length should not be equal to 0 "
            + "or exceed " + MAX_TEAM_NAME_LENGTH + " characters.";
    private final static String TEAM_MEMBERS_MESSAGE =
            "The team size should not be equal to 0 "
            + "or exceed " + MAX_NUMBER_OF_TEAM_MEMBERS + " members.";
    private final static Logger LOGGER = LogManager.getLogger(Team.class);

    private String name = "";
    private List<Employee> team = null;

    public Team() {}

    public Team(String name, List<Employee> team)
            throws ZeroOrTooManyCharactersException, TooManyPersonsException {
        if (name.isEmpty() || name.length() > MAX_TEAM_NAME_LENGTH) {
            LOGGER.error(TEAM_NAME_MESSAGE);
            throw new ZeroOrTooManyCharactersException(TEAM_NAME_MESSAGE);
        }

        if (team.isEmpty() || team.size() > MAX_NUMBER_OF_TEAM_MEMBERS) {
            LOGGER.error(TEAM_MEMBERS_MESSAGE);
            throw new TooManyPersonsException(TEAM_MEMBERS_MESSAGE);
        }

        this.name = name;
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws ZeroOrTooManyCharactersException {
        if (name.isEmpty() || name.length() > MAX_TEAM_NAME_LENGTH) {
            LOGGER.error(TEAM_NAME_MESSAGE);
            throw new ZeroOrTooManyCharactersException(TEAM_NAME_MESSAGE);
        }

        this.name = name;
    }

    public List<Employee> getTeam() {
        return team;
    }

    public void setTeam(List<Employee> team) throws TooManyPersonsException {
        if (team.isEmpty() || team.size() > MAX_NUMBER_OF_TEAM_MEMBERS) {
            LOGGER.error(TEAM_MEMBERS_MESSAGE);
            throw new TooManyPersonsException(TEAM_MEMBERS_MESSAGE);
        }

        this.team = team;
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

        Team anotherTeam = (Team) object;
        return Objects.equals(getName(), anotherTeam.getName())
                && Objects.equals(getTeam(), anotherTeam.getTeam());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getTeam(), MASK);
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + getName() + '\''
                + ", team=" + getTeam()
                + '}';
    }
}

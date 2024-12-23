package com.solvd.itcompany;

import com.solvd.itcompany.exceptions.DateTimeException;
import com.solvd.itcompany.exceptions.TooManyPersonsException;
import com.solvd.itcompany.exceptions.ZeroOrTooManyCharactersException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class JobInterview extends Meeting {
    private final static int MASK = 22;

    private Person jobApplicant = null;

    public JobInterview() {}

    public JobInterview(String name, LocalDateTime startDateTime,
                        LocalDateTime endDateTime, List<Person> participants, Person jobApplicant)
                        throws ZeroOrTooManyCharactersException, DateTimeException,
                        TooManyPersonsException {
        super(name, startDateTime, endDateTime, participants);
        this.jobApplicant = jobApplicant;
    }

    public Person getJobApplicant() {
        return jobApplicant;
    }

    public void setJobApplicant(Person jobApplicant) {
        this.jobApplicant = jobApplicant;
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

        JobInterview that = (JobInterview) object;
        return Objects.equals(getName(), that.getName())
                && getStartDateTime().isEqual(that.getStartDateTime())
                && Objects.equals(getParticipants(), that.getParticipants())
                && Objects.equals(getJobApplicant(), that.getJobApplicant());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getStartDateTime(), getParticipants(),
                            getJobApplicant(), MASK);
    }

    @Override
    public String toString() {
        return "JobInterview{"
                + "jobApplicant=" + getJobApplicant()
                + ", jobInterviewName='" + getName() + '\''
                + ", startDateTime='" + getStartDateTime() + '\''
                + ", endDateTime='" + getEndDateTime() + '\''
                + ", participants=" + getParticipants()
                + '}';
    }
}

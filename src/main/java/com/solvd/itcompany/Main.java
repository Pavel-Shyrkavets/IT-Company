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
import com.solvd.itcompany.enums.ContractType;
import com.solvd.itcompany.enums.ProgLanguage;
import com.solvd.itcompany.enums.RhTypeBloodFactor;
import com.solvd.itcompany.exceptions.DateTimeException;
import com.solvd.itcompany.exceptions.ZeroOrTooManyCharactersException;
import com.solvd.itcompany.exceptions.ZeroOrTooManyUSDException;
import com.solvd.itcompany.interfaces.IConsume;
import com.solvd.itcompany.interfaces.IFunction;
import com.solvd.itcompany.interfaces.IPredicate;
import com.solvd.itcompany.interfaces.IProcess;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * The Main class implements an application that creates a software developer,
 * an employee job contract, a CEO to invoke some methods to print some
 * information to the standard output.
 *
 * @version    1.7 15 Dec 2024
 * @author     Pavel Shyrkavets
 */
public class Main {
    private final static Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        Path outputFile = Path.of("logs/output_file.txt");

        try (OutputStream out = Files.newOutputStream(outputFile);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out))) {
            EmployeeJobContract contractOfCEO = new EmployeeJobContract(
                                                        ContractType.valueOf("EMPLOYEE_JOB_CONTRACT").toString(),
                                                        LocalDate.now(), "CEO",
                                                        new BigDecimal("50000.00"));
            EmployeeJobContract devContract = new EmployeeJobContract(
                                                        ContractType.valueOf("EMPLOYEE_JOB_CONTRACT").toString(),
                                                        LocalDate.now(), "Senior Java Developer",
                                                        new BigDecimal("10000.00"));
            CEO ceo = new CEO("Duncan", "MacLeod", LocalDate.of(1959, 5, 29),
                              "+48500000000", "d.macleod@gmail.com",
                              contractOfCEO, contractOfCEO.getPositionName(),
                              contractOfCEO.getSalaryInUSDPerMonth());
            Employee softwareDeveloper;
            List<String> programmingLanguages = new ArrayList<>();
            EmployeeComparator employeeComparator = new EmployeeComparator();
            Set<Employee> uniqueEmployees = new TreeSet<>(employeeComparator);
            Deque<Employee> employeesInDeque;
            Queue<Employee> queuedEmployees;
            CustomLinkedList<Employee> employees = new CustomLinkedList<>();

            programmingLanguages.add(ProgLanguage.valueOf("JAVA").toString());
            softwareDeveloper = new SoftwareDeveloper("Peter", "Parker", LocalDate.of(1988, 5, 26),
                                                      "+48700000000", "p.parker@gmail.com",
                                                      devContract, devContract.getPositionName(),
                                                      devContract.getSalaryInUSDPerMonth(),
                                                      programmingLanguages);

            LOGGER.info(ceo.signNewContract(softwareDeveloper, devContract));
            LOGGER.info("{} {}{}", softwareDeveloper.getFirstName(),
                        softwareDeveloper.getLastName(), softwareDeveloper.doJob());
            LOGGER.info("{} {} earns {} USD per month.", softwareDeveloper.getFirstName(),
                        softwareDeveloper.getLastName(), softwareDeveloper.getSalaryInUSDPerMonth());
            writer.write(ceo.signNewContract(softwareDeveloper, devContract));
            writer.write("\n" + softwareDeveloper.getFirstName() + " "
                         + softwareDeveloper.getLastName() + softwareDeveloper.doJob());
            writer.write("\n" + softwareDeveloper.getFirstName() + " "
                         + softwareDeveloper.getLastName() + " earns "
                         + softwareDeveloper.getSalaryInUSDPerMonth() + " USD per month.");

            uniqueEmployees.add(ceo);
            uniqueEmployees.add(softwareDeveloper);
            uniqueEmployees.add(softwareDeveloper);
            uniqueEmployees
                    .stream()
                    .filter(employee -> employee.getSalaryInUSDPerMonth()
                                                 .equals(new BigDecimal("50000.00")))
                    .forEach(employee -> LOGGER.info("CEO: {} {}", employee.getFirstName(),
                                                              employee.getLastName()));

            employeesInDeque = new ArrayDeque<>(uniqueEmployees);
            LOGGER.info(employeesInDeque.getFirst());
            LOGGER.info(employeesInDeque.getLast());
            writer.write("\n" + employeesInDeque.getFirst());
            writer.write("\n" + employeesInDeque.getLast());

            queuedEmployees = new ArrayDeque<>(employeesInDeque);
            LOGGER.info("Staff: {}", queuedEmployees.size());
            writer.write("\n" + "Staff: " + queuedEmployees.size());

            softwareDeveloper.setBloodType(new BloodType(ABOBloodGroup.A,
                                                         RhTypeBloodFactor.RH_POSITIVE));
            ceo.setBloodType(new BloodType(ABOBloodGroup.B, RhTypeBloodFactor.RH_NEGATIVE));
            employees.addFirst(softwareDeveloper);
            employees.addLast(ceo);
            LOGGER.info("{} {} {}", employees.getFirst().getValue().getFirstName(),
                        employees.getFirst().getValue().getLastName(),
                        employees.getFirst().getValue().getBloodType());
            LOGGER.info("{} {} {}", employees.getLast().getValue().getFirstName(),
                        employees.getLast().getValue().getLastName(),
                        employees.getLast().getValue().getBloodType());
            writer.write("\n" + employees.getFirst().getValue().getFirstName()
                         + " " + employees.getFirst().getValue().getLastName()
                         + " " + employees.getFirst().getValue().getBloodType());
            writer.write("\n" + employees.getLast().getValue().getFirstName()
                         + " " + employees.getLast().getValue().getLastName()
                         + " " + employees.getLast().getValue().getBloodType());

            queuedEmployees
                    .stream()
                    .collect(Collectors.groupingBy(Employee::getSalaryInUSDPerMonth))
                    .forEach((k, v) -> LOGGER.info("{}: {}", k, v));

            Supplier<String> bloodType = () -> queuedEmployees.element().getFirstName()
                                               + " " + queuedEmployees.element().getLastName()
                                               + " " + queuedEmployees.element().getBloodType().getBloodGroup()
                                               + " " + queuedEmployees.element().getBloodType().getBloodFactor();
            LOGGER.info(bloodType.get());
            writer.write("\n" + bloodType.get());
            IProcess.process(queuedEmployees,
                    employee -> employee.getBloodType().getBloodGroup() == ABOBloodGroup.B
                                         && employee.getBloodType().getBloodFactor()
                                            == RhTypeBloodFactor.RH_NEGATIVE,
                    employee -> employee.getFirstName() + " " + employee.getLastName()
                                         + " " + employee.getBloodType().getBloodGroup()
                                         + " " + employee.getBloodType().getBloodFactor(),
                             LOGGER::info);
            BiPredicate<Employee, BloodType> similarBlood =
                    (x, y) -> x.getBloodType().equals(y);
            boolean result = similarBlood.test(queuedEmployees.element(),
                                               new BloodType(ABOBloodGroup.A, RhTypeBloodFactor.RH_POSITIVE));
            LOGGER.info(result);
            writer.write("\n" + result);

            IFunction<String> firstName = (s) -> s;
            String greeting = firstName.run("Hi " + queuedEmployees.element().getFirstName());
            LOGGER.info(greeting);
            writer.write("\n" + greeting);
            IConsume<String> lastName = LOGGER::info;
            lastName.consume(queuedEmployees.element().getLastName());
            IPredicate<String> sameWord = (s) -> s.equals(queuedEmployees.element().getFirstName());
            LOGGER.info(sameWord.predicate(queuedEmployees.element().getLastName()));
            writer.write("\n" + sameWord.predicate(queuedEmployees.element().getLastName()));

            Class<?> jobInterview = Class.forName("com.solvd.itcompany.JobInterview");
            LOGGER.info(jobInterview.accessFlags());
            LOGGER.info(jobInterview.getSuperclass());
            Field[] jobInterviewDeclaredFields = jobInterview.getDeclaredFields();
            Arrays
                    .stream(jobInterviewDeclaredFields)
                    .forEach(field -> LOGGER.info("{} {} {}", field.accessFlags(),
                                                        field.getType(), field.getName()));
            Constructor<?>[] jobInterviewDeclaredConstructors =
                    jobInterview.getDeclaredConstructors();
            Arrays
                    .stream(jobInterviewDeclaredConstructors)
                    .forEach(constructor ->
                            LOGGER.info("{} {} {} {}", constructor.accessFlags(), constructor.getName(),
                                        Arrays.toString(constructor.getParameterTypes()),
                                        Arrays.toString(constructor.getExceptionTypes())));
            Method[] jobInterviewDeclaredMethods = jobInterview.getDeclaredMethods();
            Arrays
                    .stream(jobInterviewDeclaredMethods)
                    .forEach(method -> LOGGER.info("{} {} {} {} {}", method.accessFlags(),
                                                           method.getReturnType(), method.getName(),
                                                           Arrays.toString(method.getParameterTypes()),
                                                           Arrays.toString(method.getExceptionTypes())));
            Constructor<Accountant> constructor = Accountant.class.getConstructor();
            Accountant accountant = constructor.newInstance();
            Method doJob = Accountant.class.getDeclaredMethod("doJob");
            LOGGER.info("The accountant{}", doJob.invoke(accountant));
            Task task = new Task("Task", LocalDateTime.now().plusDays(7));
            Field taskName = task.getClass().getDeclaredField("name");
            taskName.setAccessible(true);
            taskName.set(task, "Super Task");
            LOGGER.info(task.getName());
        } catch (ZeroOrTooManyCharactersException ex1) {
            LOGGER.error("ZeroOrTooManyCharactersException is caught.");
        } catch (DateTimeException ex2) {
            LOGGER.error("DateTimeException is caught.");
        } catch (ZeroOrTooManyUSDException ex3) {
            LOGGER.error("ZeroOrTooManyUSDException is caught.");
        } catch (IOException ex4) {
            LOGGER.error("IOException is caught.");
        } catch (ClassNotFoundException e) {
            LOGGER.error("ClassNotFoundException is caught.");
        } catch (NoSuchMethodException e) {
            LOGGER.error("NoSuchMethodException is caught.");
        } catch (InvocationTargetException e) {
            LOGGER.error("InvocationTargetException is caught.");
        } catch (InstantiationException e) {
            LOGGER.error("InstantiationException is caught.");
        } catch (IllegalAccessException e) {
            LOGGER.error("IllegalAccessException is caught.");
        } catch (NoSuchFieldException e) {
            LOGGER.error("NoSuchFieldException is caught.");
        }
    }
}

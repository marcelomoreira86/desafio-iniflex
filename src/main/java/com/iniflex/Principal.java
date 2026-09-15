package com.iniflex;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        funcionarios.removeIf(f -> f.getNome().equals("João"));
        
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        funcionarios.forEach(f -> System.out.println(f.getNome() + " - " + f.getDataNascimento().format(fmt) + " - " + f.getSalario() + " - " + f.getFuncao()));

        funcionarios.forEach(f -> f.setSalario(f.getSalario().multiply(new BigDecimal("1.10"))));

        Map<String, List<Funcionario>> porFuncao = funcionarios.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));
        System.out.println(porFuncao);

        funcionarios.stream().filter(f -> f.getDataNascimento().getMonthValue() == 10 || f.getDataNascimento().getMonthValue() == 12).forEach(f -> System.out.println(f.getNome()));

        Funcionario maisVelho = funcionarios.stream().min(Comparator.comparing(Funcionario::getDataNascimento)).get();
        System.out.println("Mais velho: " + maisVelho.getNome() + " - " + Period.between(maisVelho.getDataNascimento(), LocalDate.now()).getYears() + " anos");

        funcionarios.sort(Comparator.comparing(Funcionario::getNome));
        funcionarios.forEach(f -> System.out.println(f.getNome()));

        BigDecimal total = funcionarios.stream().map(Funcionario::getSalario).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total: " + total);

        funcionarios.forEach(f -> System.out.println(f.getNome() + " ganha " + f.getSalario().divide(new BigDecimal("1212.00"), 2, RoundingMode.HALF_UP) + " salários mínimos"));
    }
}

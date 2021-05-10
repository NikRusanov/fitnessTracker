package com.rusanov.fitnessApp;

import com.google.common.base.Stopwatch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.EnumMap;

public class Training {

    private final EnumMap<TypeOfTraining, Double> caloriesTraining;

    public Training() {
        caloriesTraining =  new EnumMap<>(TypeOfTraining.class);
        caloriesTraining.put(TypeOfTraining.JUMP_ROPE, 400.0);
        caloriesTraining.put(TypeOfTraining.PUSH_UPS, 600.55);
        caloriesTraining.put(TypeOfTraining.SQUATS, 700.0);
    }

    private Stopwatch stopwatch;
    private Duration elapsedTime;
    private TypeOfTraining selectedTraining;
    private Double wastedCalories;

    public Double getWastedCalories() {
        return  wastedCalories;
    }

    public void start() {
        show();
        select();
        startTimer();
    }

    public void stop() {
        stopTimer();
        calculateCalories();
        showResult();
    }

    private void showResult() {
        String trainingTime;
        if (elapsedTime.toMinutes() == 0) {
            trainingTime = Long.toString(elapsedTime.toSeconds()) + "sec";
        } else {
            trainingTime = elapsedTime.toMinutes() + " min";
        }
        String builder = "Result:\nTraining type: " +
                selectedTraining +
                "\nTraining duration:\n" +
                trainingTime +
                "\nBurned calories:" +
                String.format("%.2f", wastedCalories);
        System.out.println(builder);
    }


    private void select() {
        System.out.println("\nSelect one of them: ");
        BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));
        while (true) {
            System.out.print("Input a type: ");
            try {

                selectedTraining = TypeOfTraining
                        .valueOf(
                                reader.readLine());
                break;
            } catch (IllegalArgumentException | IOException ex) {
                System.out.println("\nInvalid type! Try again.");
            }
        }
    }

    private void startTimer() {
        System.out.println("Go go go ");
        stopwatch = Stopwatch.createStarted();
    }

    private void stopTimer() {
        System.out.println("Relax now. Training finished");
        elapsedTime = this.stopwatch.elapsed();
        System.out.println("Elapsed time: " + elapsedTime.toSeconds() + " sec");
    }


    private void calculateCalories() {
        final long SECONDS_IN_HOUR = 3600;
        Double caloriesPerHour = caloriesTraining.get(selectedTraining);
        long seconds = elapsedTime.getSeconds();

        wastedCalories = (caloriesPerHour * seconds) / SECONDS_IN_HOUR;
    }


    private void show() {
        System.out.println("Accessible types of training:");
        caloriesTraining.forEach((key, value) -> System.out.printf("%s - %.2f cal\n", key, value));
    }
}

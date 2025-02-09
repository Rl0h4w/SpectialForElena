import java.util.Scanner;

public class Game {
    private int health = 7;
    private int energy = 5;
    private int magic = 3;
    private int potions = 0;
    private int runes = 3;
    private Scanner scanner;
    private boolean isFirstCampVisit = true;
    private boolean isOpenedSecret = false;
    private boolean isCameThroughForest = false;
    private boolean isCrystal = true;
    private boolean isRune = false;

    public Game() {
        this.scanner = new Scanner(System.in);
    }

    private void printStatus() {
        System.out.println("HEALTH: " + this.health);
        System.out.println("ENERGY: " + this.energy);
        System.out.println("MAGIC: " + this.magic);
        System.out.println("POTIONS: " + this.potions);
        System.out.println("RUNES: " + this.runes);
    }

    private void usePoison() {
        if (this.potions > 0) {
            this.potions--;
            this.health = Math.min(this.health + 5, 10);
        } else {
            System.out.println("no potions");
        }
    }

    private boolean checkGameOver() {
        if (this.health <= 0 || this.energy <= 0) {
            usePoison();
            if (this.health <= 0 || this.energy <= 0) {
                return true;
            }
        }
        return false;
    }

    private void showCampMenu() {

        /*
         * добавь что чел получает при заходе в оагерь
         * добавь трату кристалла(можно не тратить
         */

        System.out.println("Вы прибыли в небольшой лагерь");
        System.out.println("Доступные действия:");
        System.out.println("1: Посмотреть информацию о персонаже");

        if (this.isFirstCampVisit) {
            System.out.println("2: Взять карту и изучить маршрут");
            System.out.println("3: Использовать магический кристалл");
            System.out.println("4: Осмотреть лагерь");
            System.out.println("5: Выйти из лагеря");
        } else {
            System.out.println("2: Взять карту и изучить маршрут");
            System.out.println("3: Использовать зелье");
            System.out.println("4: Выйти из лагеря");
        }
        System.out.print("Введите номер действия: ");
    }

    private void exploreCamp() {
        boolean exitFlag = false;
        while (!exitFlag) {
            showCampMenu();
            int moveInCamp;
            moveInCamp = scanner.nextInt();
            if (this.isFirstCampVisit) {
                switch (moveInCamp) {
                    case 1:
                        this.printStatus();
                        break;
                    case 2:
                        System.out.println(
                                "Вы открыли карту, вам стало легче от подсказок + энергия");
                        this.energy += 2;
                        System.out.println(
                                "На карте отмечены три возможных пути к первым рунам: один через густой лес, другой — по старой заброшенной дороге, третий — скрытая тропа, найденная рядом с лагерем");
                        System.out.println(
                                "Густой лес полон ловушек, но в нём есть полезные ресурсы. Старый путь безопасен, но долго обходить. Скрытая тропа — наиболее безопасна, но её сложно найти без магии");
                        System.out.println(
                                "Вижу, что на карте рядом с вами есть магический источник. Пройди его, если хочешь увеличить свои силы");
                        break;
                    case 3:
                        System.out.println("Вы сканируете с помощью кристалла ближайшую территорию.");
                        System.out.println("герой находит скрытую тропу, открывая альтернативный путь к первой руне");
                        this.isOpenedSecret = true;
                        break;
                    case 4:
                        System.out.println("Вы находите зелье, но тратите время и энергию.");
                        this.energy -= 1;
                        this.potions++;
                        break;
                    case 5:
                        this.isFirstCampVisit = false;
                        System.out.println("Вы покинули лагерь.");
                        exitFlag = true;
                        break;
                    default:
                        System.out.println("От 1 до 5 включительно");
                        break;
                }
            } else {
                switch (moveInCamp) {
                    case 1:
                        this.printStatus();
                        break;
                    case 2:
                        System.out.println(
                                "Вы открыли карту, вам стало легче от подсказок + энергия");
                        this.energy += 2;
                        System.out.println(
                                "На карте отмечены три возможных пути к первым рунам: один через густой лес, другой — по старой заброшенной дороге, третий — скрытая тропа, найденная рядом с лагерем");
                        System.out.println(
                                "Густой лес полон ловушек, но в нём есть полезные ресурсы. Старый путь безопасен, но долго обходить. Скрытая тропа — наиболее безопасна, но её сложно найти без магии");
                        System.out.println(
                                "Вижу, что на карте рядом с вами есть магический источник. Пройди его, если хочешь увеличить свои силы");
                        break;
                    case 3:
                        if (this.potions > 0) {
                            System.out.println(
                                    "Вы пьёте горькое, ужастно пахнущее отвратительное зелье, однако вам полегчало");
                            usePoison();
                        } else {
                            System.out.println("Вы хотите выпить, а выпить нечего");
                        }
                        break;
                    case 4:
                        this.isFirstCampVisit = false;
                        System.out.println("Вы покинули лагерь.");
                        exitFlag = true;
                        break;
                    default:
                        System.out.println("От 1 до 4х включительно");
                        break;
                }
            }
        }
    }

    private void exploreForest() {
        boolean exitFlag = false;
        while (!exitFlag) {
            System.out.println("Вы углубляетесь в лес, будьте предельно осторожны.");
            System.out.println("Доступные действия: ");
            System.out.println("1. Следовать основным путём через густой лес");
            System.out.println("2. Вызвать магического фамильяра для разведки пути.");
            if (this.isOpenedSecret) {
                System.out.println("3. Пойти по скрытой тропе, обнаруженной в лагере");
            }

            int userChoice = scanner.nextInt();
            if (!isOpenedSecret && userChoice == 3) {
                userChoice = 0;
            }

            switch (userChoice) {
                case 1:
                    if (this.health >= 3 && this.energy >= 2) {
                        System.out.println(
                                "Вы уверенно идёте по основному пути, избегая некоторых ловушек, но несколько всё-таки срабатывают. К счастью, вы не получили серьёзных травм, и ваш путь продолжается");
                        System.out.println(
                                "После прохождения через густой лес Вы теряете 3 здоровья, но находите магический амулет и получаете +2 к энергии.");
                        this.health -= 3;
                        this.energy += 2;
                        this.isCameThroughForest = true;
                        checkGameOver();
                        exitFlag = true;
                        break;

                    } else {
                        System.out.println(
                                "Вы не успеваете избежать всех ловушек, и несколько из них активируются. Вам удаётся выбраться, но ваши силы на исходе.");
                        System.out.println("Вы теряете 5 здоровья и 2 энергии");
                        this.health -= 5;
                        this.energy -= 2;
                        this.isCameThroughForest = true;
                        checkGameOver();
                        exitFlag = true;
                        break;
                    }

                case 2:
                    if (this.magic >= 2) {
                        System.out.println(
                                "Вы вызываете своего фамильяра, и он, используя свои способности, находит безопасный путь через лес, избегая большинства опасностей");
                        System.out.println(
                                "На вызов фамильяра Вы потратили 2 магии, но благодаря этому получили 3 здоровья");
                        this.magic -= 2;
                        this.health += 3;
                        this.isCameThroughForest = true;
                        checkGameOver();
                        exitFlag = true;
                        break;

                    } else {
                        System.out.println(
                                "Магия фамильяра не срабатывает должным образом, и вы теряете силы, не получив нужной помощи.");
                        System.out.println("Вы теряете 2 магии и 2 энергии");
                        this.health -= 2;
                        this.magic -= 2;
                        checkGameOver();
                        exitFlag = true;
                        break;
                    }

                case 3:
                    if (this.energy >= 2) {
                        System.out.println(
                                "Вы выбрали скрытую тропу, и, благодаря подсказкам на карте, смогли избежать ловушек и не потерять много сил");
                        System.out.println("Вы получаете +2 к энергии");
                        this.isCameThroughForest = true;
                        this.energy += 2;
                        exitFlag = true;
                        checkGameOver();
                        break;

                    } else {
                        System.out.println(
                                "Скрытая тропа оказывается полна неожиданных магических ловушек. Вы теряете драгоценную энергию, пытаясь от них защититься");
                        System.out.println("Вы теряете 3 энергии и 1 здоровье");
                        this.health -= 1;
                        this.energy -= 3;
                        this.isCameThroughForest = true;
                        checkGameOver();
                        exitFlag = true;
                        break;
                    }

                default:
                    if (this.isOpenedSecret) {
                        System.out.println("Введите цифру от 1 до 3");
                        break;

                    } else {
                        System.out.println("Введите цифру от 1 до 2");
                        break;
                    }
            }
        }
    }

    private void inspectAltar() {
        boolean exitFlag = false;
        while (!exitFlag) {
            System.out.println("После путешествия через лес Вы находите древний алтарь, охраняющий руну");
            System.out.println("Доступные действия: ");
            System.out.println("1. Исследовать символы на алтаре.");
            System.out.println("2. Попытаться разрушить алтарь.");
            if (this.isCrystal) {
                System.out.println("3. Использовать магический кристалл, найденный ранее.");
            }

            int userChoice = scanner.nextInt();
            if (!this.isCrystal && userChoice == 3) {
                userChoice = 0;
            }

            switch (userChoice) {
                case 1:
                    if (this.energy >= 3 && this.magic >= 1) {
                        System.out.println(
                                "Вы внимательно изучаете символы и, приложив усилия, расшифровываете их значение. Это открывает секретный механизм и позволяет вам получить первую руну без лишних трудностей");
                        System.out.println("После изучения вы получаете +2 к энергии и +1 к магии");
                        this.energy += 2;
                        this.magic += 1;
                        this.isRune = true;
                        checkGameOver();
                        exitFlag = true;
                        break;

                    } else {
                        System.out.println(
                                "Ваши попытки расшифровать символы не приводят к успеху. Алтарь выбрасывает магический импульс, и вы теряете несколько сил");
                        System.out.println("Вы потеряли 3 здоровья :(");
                        this.health -= 3;
                        checkGameOver();
                        exitFlag = true;
                        break;
                    }

                case 2:
                    if (this.health >= 3 && this.magic >= 3) {
                        System.out.println(
                                "Вы решаете разрушить алтарь и, используя свою магическую мощь, разрушаете его. Это даёт вам доступ к первой руне");
                        System.out.println("Вы теряете 5 здоровья, но получаете руну");
                        this.health -= 5;
                        this.isRune = true;
                        checkGameOver();
                        exitFlag = true;
                        break;

                    } else {
                        System.out.println(
                                "Ваши попытки разрушить алтарь не увенчались успехом. Алтарь выбрасывает мощный магический импульс, отбрасывая вас назад");
                        System.out.println("Из-за нехватки ресурсов алтарь не повреждается, ВЫ теряете 3 энергии");
                        this.energy -= 3;
                        checkGameOver();
                        exitFlag = true;
                        break;
                    }

                case 3:
                    if (this.energy >= 2) {
                        System.out.println(
                                "Вы активируете кристалл, и его свет облучает алтарь. Он указывает путь к руне, и вы успеваете пройти к ней без потерь");
                        System.out.println(
                                "После использования кристалла ВЫ восстанавливаете 3 здоровья, теряя 2 энергии");
                        this.health += 3;
                        this.energy -= 2;
                        this.isRune = true;
                        checkGameOver();
                        exitFlag = true;
                        break;

                    } else {
                        System.out.println(
                                "Когда вы активировали кристалл, его энергия не совпала с энергией алтаря, что привело к небольшому откату магии. Портал остаётся заблокированным");
                        System.out.println("Вы теряете 1 энергию и 1 здоровье");
                        this.health -= 1;
                        this.energy -= 1;
                        checkGameOver();
                        exitFlag = true;
                        break;
                    }

                default:
                    if (isCrystal) {
                        System.out.println("Введите число от 1 до 3");
                        break;
                    } else {
                        System.out.println("Введите число от 1 до 3");
                        break;
                    }
            }
        }
    }

    private void finalPortal() {
        System.out.println(
                "Найдя руну, Вы возвращаетесь к порталу. Портал активируется, но лес обрушивает на Вас последнее испытание.");
        System.out.println("Осталось только одно: использовать все оставшиеся ресурсы, чтобы активировать портал.");
        if (this.isRune && this.health >= 5 && this.energy >= 3 && this.magic >= 1) {
            System.out.println("Вы открываете портал и проходите через него, разгадка раскрыта!");
        } else {
            System.out.println(
                    "Ресурсов не хватает: Портал не активируется, вы можете завершить задание, пополнив ресурсы.");
        }
    }
}
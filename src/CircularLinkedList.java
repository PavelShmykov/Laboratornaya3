public class CircularLinkedList {

    public static void main(String[] args) {
        CircularLinkedList list = new CircularLinkedList();

        System.out.println("Тест addFirst и addLast");
        list.addFirst(10);
        list.addLast(20);
        list.addLast(30);
        list.addFirst(5);
        list.display();

        System.out.println("\nТест size и isEmpty");
        System.out.println("Размер: " + list.size());
        System.out.println("Пустой ли список: " + list.isEmpty());

        System.out.println("\nТест contains");
        System.out.println("Содержит ли список 20: " + list.contains(20));
        System.out.println("Содержит ли список 99 " + list.contains(99));

        System.out.println("\nТест find");
        CircularLinkedList.Node found = list.find(30);
        System.out.println(found != null ? "Нашли: " + found.data : "Не нашли");

        System.out.println("\nТест removeFirst");
        list.removeFirst();
        list.display();

        System.out.println("\nТест removeLast");
        list.removeLast();
        list.display();

        System.out.println("\nТест remove по значению");
        list.remove(10);
        list.display();

        System.out.println("\nТест rotate");
        list.addLast(40);
        list.addLast(50);
        list.display();
        list.rotate();
        list.display();

        System.out.println("\nТест findCycle");
        System.out.println("Есть ли цикл в списке: " + list.findCycle());

        System.out.println("\nsplitIntoTwo");
        list.clear();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        list.addLast(6);
        list.display();

        CircularLinkedList[] parts = list.splitIntoTwo();
        System.out.print("Первая часть: ");
        parts[0].display();
        System.out.print("Вторая часть: ");
        parts[1].display();

        System.out.println("\nТест clear");
        list.clear();
        list.display();
        System.out.println("Размер после clear: " + list.size());
    }

    class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private Node tail;

    // Добавление элемента в начало списка
    public void addFirst(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
            tail.next = head;
        } else {
            newNode.next = head;
            head = newNode;
            tail.next = head;
        }
    }

    // Добавление элемента в конец списка
    public void addLast(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
            tail.next = head; // замыкаем круг
        } else {
            tail.next = newNode;
            tail = newNode;
            tail.next = head;
        }
    }

    // Удаление первого элемента
    public void removeFirst() {
        if (head == null) {
            System.out.println("Список пуст, нечего удалять!");
            return;
        }

        // Если в списке только один элемент
        if (head == tail) {
            head = null;
            tail = null;
            return;
        }

        head = head.next;     // смещаем голову на следующий узел
        tail.next = head;     // хвост теперь указывает на новую голову
    }

    // Удаление последнего элемента
    public void removeLast() {
        if (head == null) {
            System.out.println("Список пуст, нечего удалять!");
            return;
        }

        // Если в списке только один элемент
        if (head == tail) {
            head = null;
            tail = null;
            return;
        }

        Node currentNode = head;
        // идём до предпоследнего узла (тот, чей next = tail)
        while (currentNode.next != tail) {
            currentNode = currentNode.next;
        }

        currentNode.next = head; // замыкаем круг обратно на голову
        tail = currentNode;      // обновляем хвост
    }

    // Удаление по значению
    public void remove(int data) {
        if (head == null) {
            System.out.println("Список пуст, нечего удалять!");
            return;
        }

        // Если в списке только один элемент
        if (head == tail && head.data == data) {
            head = null;
            tail = null;
            return;
        }

        // Проверяем голову отдельно
        if (head.data == data) {
            head = head.next;     // смещаем голову
            tail.next = head;     // хвост указывает на новую голову
            return;
        }

        Node current = head;
        Node previous = null;

        // идём по кругу до возвращения к head
        do {
            previous = current;
            current = current.next;

            if (current.data == data) {
                previous.next = current.next;
                // если удаляем хвост — обновляем tail
                if (current == tail) {
                    tail = previous;
                }
                return;
            }
        } while (current != head);
    }

    // Проверка наличия элемента
    public boolean contains(int data) {
        if (head == null) return false;

        Node current = head;
        do {
            if (current.data == data) {
                return true;
            }
            current = current.next;
        } while (current != head);

        return false;
    }

    // Размер списка
    public int size() {
        if (head == null) return 0;

        int count = 0;
        Node current = head;
        do {
            count++;
            current = current.next;
        } while (current != head);

        return count;
    }

    // Проверка на пустоту
    public boolean isEmpty() {
        return head == null;
    }

    // Вывод всех элементов списка
    public void display() {
        if (head == null) {
            System.out.println("Список пуст!");
            return;
        }

        Node current = head;
        do {
            System.out.print(current.data + " ");
            current = current.next;
        } while (current != head);
        System.out.println();
    }

    // Очистка списка
    public void clear() {
        head = null;
        tail = null;
    }

    // Циклический сдвиг списка
    public void rotate() {
        if (head == null || head == tail) {
            return;
        }

        // новый head будет следующим за старым
        head = head.next;
        tail = tail.next;
    }

    // Проверка наличия цикла
    public boolean findCycle() {
        return true;
    }

    // Поиск элемента
    public Node find(int data) {
        if (head == null) return null;

        Node current = head;
        do {
            if (current.data == data) {
                return current;
            }
            current = current.next;
        } while (current != head);

        return null;
    }

    // Разделение списка на два равных циклических списка
    public CircularLinkedList[] splitIntoTwo() {
        CircularLinkedList[] result = new CircularLinkedList[2];
        result[0] = new CircularLinkedList();
        result[1] = new CircularLinkedList();

        if (head == null || head == tail) {
            // пустой или один элемент, значит не делим
            result[0].head = head;
            result[0].tail = tail;
            if (tail != null) tail.next = head;
            return result;
        }

        // Находим середину (slow-fast алгоритм)
        Node slow = head;
        Node fast = head;

        while (fast.next != head && fast.next.next != head) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // slow — середина списка
        Node head1 = head;
        Node head2 = slow.next;

        // Формируем первый список
        result[0].head = head1;
        result[0].tail = slow;
        result[0].tail.next = head1;

        // Формируем второй список
        result[1].head = head2;
        result[1].tail = tail;
        result[1].tail.next = head2;

        return result;
    }



}

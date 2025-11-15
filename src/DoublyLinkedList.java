import java.util.Objects;

class Node<T> {
    T data;
    Node<T> prev;
    Node<T> next;

    public Node(T data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

public class DoublyLinkedList<T> {

    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();

        System.out.println("Тест addFirst и addLast");
        list.addFirst("Hello");
        list.addFirst(5);
        list.addLast(true);
        list.addLast(30);
        list.display();

        System.out.println("\nТест removeFirst");
        list.removeFirst();
        list.display();

        System.out.println("\nТест removeLast");
        list.removeLast();
        list.display();

        System.out.println("\nТест remove");
        list.addLast(40);
        list.addLast(20);
        list.addLast(20);
        list.display();
        list.remove(20);
        list.display();

        System.out.println("\nТест contains");
        System.out.println("Содержит ли список 40: " + list.contains(40));
        System.out.println("Содержит ли список 99: " + list.contains(99));

        System.out.println("\nТест size и isEmpty");
        System.out.println("Размер списка: " + list.size());
        System.out.println("Пустой ли список: " + list.isEmpty());

        System.out.println("\nТест clear");
        list.clear();
        list.display();
        System.out.println("Размер после clear: " + list.size());
        System.out.println("Пустой ли список: " + list.isEmpty());

        System.out.println("\nТест add");
        list.addFirst(1);
        list.addLast(3);
        list.add(1, "dd");
        list.display();

        System.out.println("\nТест get(index) ===");
        System.out.println("Элемент по индексу 0: " + list.get(0));
        System.out.println("Элемент по индексу 1: " + list.get(1));
        System.out.println("Элемент по индексу 2: " + list.get(2));

        System.out.println("\nТест removeByIndex");
        list.display();
        list.removeByIndex(2);
        list.display();

        System.out.println("\nТест displayReverse");
        list.displayReverse();

        System.out.println("\nТест getFirst и getLast");
        System.out.println("Первый элемент: " + list.getFirst());
        System.out.println("Последний элемент: " + list.getLast());

    }

    private Node head;
    private Node tail;

    // Добавление элемента в начало списка
    public void addFirst(T data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
    }

    // Добавление элемента в конец списка
    public void addLast(T data) {
        Node newNode = new Node(data);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    // Удаление первого элемента
    public void removeFirst() {
        if (head == null) {
            System.out.println("Список пуст, нечего удалять!");
            return;
        }
        if (head == tail) { // если один элемент
            head = null;
            tail = null;
        } else {
            head = head.next;   // смещаем голову
            head.prev = null;   // обнуляем ссылку назад
        }
    }

    // Удаление последнего элемента
    public void removeLast() {
        if (tail == null) {
            System.out.println("Список пуст, нечего удалять!");
            return;
        }
        if (head == tail) { // если один элемент
            head = null;
            tail = null;
        } else {
            tail = tail.prev;   // смещаем хвост
            tail.next = null;   // обнуляем ссылку вперёд
        }
    }

    // Удаление всех элементов по значению
    public void remove(T data) {
        if (head == null) {
            System.out.println("Список пуст, нечего удалять!");
            return;
        }

        Node current = head;

        while (current != null) {
            if (Objects.equals(current.data, data)) {
                if (current == head) {
                    removeFirst();
                    current = head; // после удаления головы начинаем с новой
                    continue;
                } else if (current == tail) {
                    removeLast();
                    current = null; // хвост удалён, список закончен
                    continue;
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
            }
            current = current.next;
        }

    }

    // Проверка наличия элемента
    public boolean contains(T data) {
        Node current = head;
        while (current != null) {
            if (Objects.equals(current.data, data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Вывод списка
    public void display() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " <-> ");
            current = current.next;
        }
        System.out.println("null");
    }

    // 7. size() - возврат размера списка
    public int size() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    // 8. isEmpty() - проверка на пустоту
    public boolean isEmpty() {
        return head == null; // если голова отсутствует, список пуст
    }

    // 10. clear() - очистка списка
    public void clear() {
        head = null;
        tail = null;
        // ссылки на узлы обнулятся автоматически при сборке мусора
    }

    // Вставка элемента по индексу
    public void add(int index, T data) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс не может быть отрицательным");
        }

        Node newNode = new Node<>(data);

        if (index == 0) { // вставка в начало
            addFirst(data);
            return;
        }

        Node current = head;
        int count = 0;
        while (current != null && count < index) {
            current = current.next;
            count++;
        }

        if (current == null) { // вставка в конец
            addLast(data);
        } else {
            newNode.prev = current.prev;
            newNode.next = current;
            current.prev.next = newNode;
            current.prev = newNode;
        }
    }

    // Удаление элемента по индексу
    public void removeByIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс не может быть отрицательным");
        }

        if (head == null) {
            System.out.println("Список пуст, нечего удалять!");
            return;
        }

        if (index == 0) { // удаление первого
            removeFirst();
            return;
        }

        Node current = head;
        int count = 0;
        while (current != null && count < index) {
            current = current.next;
            count++;
        }

        if (current == null) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона");
        } else if (current == tail) {
            removeLast();
        } else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }
    }


    // Получение элемента по индексу
    public Object get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс не может быть отрицательным");
        }

        Node current = head;
        int count = 0;
        while (current != null) {
            if (count == index) {
                return current.data;
            }
            current = current.next;
            count++;
        }
        throw new IndexOutOfBoundsException("Индекс вне диапазона");
    }

    // Вывод элементов в обратном порядке
    public void displayReverse() {
        Node current = tail;
        while (current != null) {
            System.out.print(current.data + " <-> ");
            current = current.prev;
        }
        System.out.println("null");
    }

    // Получение первого элемента
    public Object getFirst() {
        if (head == null) {
            throw new RuntimeException("Список пуст");
        }
        return head.data;
    }

    // Получение последнего элемента
    public Object getLast() {
        if (tail == null) {
            throw new RuntimeException("Список пуст");
        }
        return tail.data;
    }


}

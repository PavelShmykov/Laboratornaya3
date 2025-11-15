public class SinglyLinkedList {

    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();

        System.out.println("addFirst и addLast");
        list.addFirst(10);
        list.addFirst(5);
        list.addLast(20);
        list.addLast(30);
        list.display();

        System.out.println("\nТест removeFirst");
        list.removeFirst();
        list.display();

        System.out.println("\nТест removeLast");
        list.removeLast();
        list.display();

        System.out.println("\nТест remove");
        list.addLast(10);
        list.addLast(40);
        list.addLast(10);
        list.display();
        list.remove(10);
        list.display();

        System.out.println("\nТест contains");
        System.out.println("Содержит ли список 20: " + list.contains(20));
        System.out.println("Содержит ли список 99 " + list.contains(99));

        System.out.println("\nТест size");
        System.out.println("Размер списка: " + list.size());
        list.display();

        System.out.println("\nТест isEmpty");
        System.out.println("Пустой ли список: " + list.isEmpty()); // false

        System.out.println("\nТест clear");
        list.clear();
        list.display();
        System.out.println("Пустой ли список: " + list.isEmpty());
        System.out.println("Размер списка: " + list.size()); // 0
    }

    private Node head; // ссылка на первый элемент

    public class Node {
        int data;
        Node next;


        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Добавление элемента в начало списка
    public void addFirst(int data) {
        Node newNode = new Node(data);
        newNode.next = head; // новый узел указывает на текущую голову
        head = newNode;      // голова теперь новый узел
    }

    // Добавление элемента в конец списка
    public void addLast(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode; // если список пуст, то новый узел становится головой
            return;
        }
        Node currentNode = head;
        while (currentNode.next != null) { // идём до последнего узла
            currentNode = currentNode.next;
        }
        currentNode.next = newNode; // последний узел указывает на новый
    }

    // Удаление первого элемента
    public void removeFirst() {
        if (head == null) {
            System.out.println("Список пуст, нечего удалять!");
            return;
        }
        head = head.next; // просто смещаем голову на следующий узел
    }

    // Удаление последнего элемента
    public void removeLast() {
        if (head == null) {
            System.out.println("Список пуст, нечего удалять!");
            return;
        }
        if (head.next == null) {
            head = null; // если в списке только один элемент
            return;
        }
        Node currentNode = head;
        while (currentNode.next.next != null) { // идём до предпоследнего узла
            currentNode = currentNode.next;
        }
        currentNode.next = null; // обрываем ссылку на последний узел
    }

    // Удаление по значению
    public void remove(int data) {
        if (head == null) {
            System.out.println("Список пуст, нечего удалять!");
            return;
        }

        Node currentNode = head;
        Node previousNode = null;

        // сначала проверяем голову отдельно
        if (head.data == data) {
            head = head.next;
            currentNode = head;
        }

        // идём по списку
        while (currentNode != null) {
            if (currentNode.data == data) {
                // удаляем текущий узел
                previousNode.next = currentNode.next;
            } else {
                // двигаем previousNode только если не удалили
                previousNode = currentNode;
            }
            currentNode = currentNode.next;
        }
    }


    // Проверка наличия элемента
    public boolean contains(int data) {
        Node current = head;
        while (current != null) {
            if (current.data == data) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Размер списка
    public int size() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    // Проверка на пустоту
    public boolean isEmpty() {
        return head == null;
    }

    // Вывод всех элементов списка
    public void display() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    // Очистка списка
    public void clear() {
        head = null;
    }

}

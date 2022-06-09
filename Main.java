class Main {
  public static void main(String[] args) {
    App app = new App();
    try {
      app.run();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}

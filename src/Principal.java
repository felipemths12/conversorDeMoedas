import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String apiKey = "4d84160cc40eb1d2c5aec17b";
        Requisicao service = new Requisicao(apiKey);

        String menu = """
                **************************************
                Selecione uma opção de conversão:
                1 - Real ==> Dólar Americano
                2 - Dólar Americano ==> Real
                3 - Real ==> Euro
                4 - Euro ==> Real
                5 - Dólar Americano ==> Euro
                6 - Euro ==> Dólar Americano
                7 - Sair
                **************************************
                """;

        System.out.println("Seja bem-vindo ao Conversor de Moedas!\n");

        int opc;
        do {
            System.out.println(menu);
            opc = sc.nextInt();
            sc.nextLine(); // limpar buffer

            if (opc >= 1 && opc <= 6) {
                System.out.print("Digite o valor a ser convertido: ");
                double valor = sc.nextDouble();
                sc.nextLine();

                String origem = "", destino = "";
                switch (opc) {
                    case 1 -> { origem = "BRL"; destino = "USD"; }
                    case 2 -> { origem = "USD"; destino = "BRL"; }
                    case 3 -> { origem = "BRL"; destino = "EUR"; }
                    case 4 -> { origem = "EUR"; destino = "BRL"; }
                    case 5 -> { origem = "USD"; destino = "EUR"; }
                    case 6 -> { origem = "EUR"; destino = "USD"; }
                }

                try {
                    Resposta response = service.getConversao(origem);
                    double taxa = response.conversion_rates().get(destino);
                    double convertido = Conversor.converter(valor, taxa);

                    System.out.printf("Resultado: %.2f %s = %.2f %s%n",
                            valor, origem, convertido, destino);
                } catch (Exception e) {
                    System.err.println("Erro na conversão: " + e.getMessage());
                }
            } else if (opc != 7) {
                System.out.println("Opção inválida");
            }

        } while (opc != 7);

        System.out.println("Obrigado por usar o conversor de moedas!");
    }
}
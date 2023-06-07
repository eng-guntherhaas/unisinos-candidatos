import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class PrincipalCandidatos {
    public static void main(String[] args) {
        Random random = new Random();
        Candidato[] candidatos = new Candidato[random.nextInt(100) + 1];

        for (int i = 0; i < candidatos.length; i++) {
            String nome = "Candidato " + (i + 1);
            String partido = "Partido " + (random.nextInt(10) + 1);
            int intencoesVotos = random.nextInt(1000) + 1;
            candidatos[i] = new Candidato(nome, partido, intencoesVotos);
        }

        ordenaCandidatosPorNome(candidatos);
        System.out.println("Ordenado por nome:");
        imprimeCandidatos(candidatos);

        ordenaCandidatosPorVotos(candidatos);
        System.out.println("\nOrdenado por intenções de voto:");
        imprimeCandidatos(candidatos);

        ordenaCandidatosPorPartido(candidatos);
        System.out.println("\nOrdenado por partido:");
        imprimeCandidatos(candidatos);

        String nomeProcurado = "Candidato " + (random.nextInt(candidatos.length) + 1);
        int posicao = pesquisaBinariaCandidatos(candidatos, nomeProcurado);
        if (posicao == -1) {
            System.out.println("\nNão foi encontrado nenhum candidato com o nome " + nomeProcurado);
        } else {
            System.out.println("\nO candidato " + nomeProcurado + " foi encontrado na posição " + posicao);
            System.out.println(candidatos[posicao]);
        }
    }

    public static void ordenaCandidatosPorNome(Candidato[] candidatos) {
        Arrays.sort(candidatos, Comparator.comparing(Candidato::getNome)
                .thenComparing(Candidato::getIntencoesVotos)
                .thenComparing(Candidato::getPartido));
    }

    public static void ordenaCandidatosPorVotos(Candidato[] candidatos) {
        Arrays.sort(candidatos, Comparator.comparing(Candidato::getIntencoesVotos)
                .reversed()
                .thenComparing(Candidato::getNome)
                .thenComparing(Candidato::getPartido));
    }

    public static void ordenaCandidatosPorPartido(Candidato[] candidatos) {
        Arrays.sort(candidatos, Comparator.comparing(Candidato::getPartido)
                .thenComparing(Candidato::getNome)
                .thenComparing(Candidato::getIntencoesVotos));
    }

    public static void imprimeCandidatos(Candidato[] candidatos) {
        for (Candidato candidato : candidatos) {
            System.out.println(candidato);
            System.out.println();
        }
    }

    public static int pesquisaBinariaCandidatos(Candidato[] candidatos, String nomeProcurado) {
        int esquerda = 0;
        int direita = candidatos.length - 1;

        while (esquerda <= direita) {
            int meio = (esquerda + direita) / 2;

            if (candidatos[meio].getNome().equals(nomeProcurado)) {
                return meio;
            } else if (candidatos[meio].getNome().compareTo(nomeProcurado) < 0) {
                esquerda = meio + 1;
            } else {
                direita = meio - 1;
            }
        }

        return -1;
    }
}

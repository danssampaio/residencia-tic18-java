package com.dansoft.empresaCoelho.view;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Scanner;

import com.dansoft.empresaCoelho.controller.CCliente;
import com.dansoft.empresaCoelho.controller.CImovel;
import com.dansoft.empresaCoelho.model.Cliente;
import com.dansoft.empresaCoelho.model.Imovel;
import com.dansoft.validations.Validations;

public class VImovel {
	private static Scanner scanner = new Scanner(System.in);
	
	public static void menuImovel() throws Exception {
		String option = null;

		do {

			System.out.println("\n----- Menu Imóvel -----\n" + "1. Cadastrar Imóvel\n" + "2. Editar Imóvel\n"
					+ "3. Excluir Imóvel\n" + "4. Listar Imóveis\n" + "5. Listar Imóveis de um Cliente\n"
					+ "6. Voltar ao Menu Principal\n");
			System.out.print("Escolha uma opção: ");

			option = scanner.nextLine();

			if (Main.isInt(option)) {
				switch (option) {
				case "1":
					cadastrarImovel();
					break;
				case "2":
					editarImovel();
					break;
				case "3":
					excluirImovel();
					break;
				case "4":
					listarImoveis();
					break;
				case "5":
					listarImoveisPorCliente();
					break;
				case "6":
					System.out.println("Voltando ao Menu Principal.\n");
					break;
				default:
					System.out.println("Opção inválida. Tente novamente.\n");
				}
			} else {
				System.out.println("Somente números.\n");
			}

		} while (!option.equals("6"));
	}

	private static void cadastrarImovel() {
		try {
			Validations validationCpf = new Validations();
			Cliente cliente = null;
			Imovel imovelAdd = new Imovel();

			System.out.print("Endereço: ");
			String endereco = scanner.nextLine();

			imovelAdd.setEndereco(endereco);
			imovelAdd.setMatricula(Main.gerarCodigoAleatorio());
			imovelAdd.setUltimaLeitura("0");
			imovelAdd.setPenultimaLeitura("0");

			System.out.print("CPF do morador(XXX.XXX.XXX-XX): ");
			String cpf = scanner.nextLine();

			if (!validationCpf.isValidCpf(cpf)) {
				System.out.println("CPF inválido.\n");
				return;
			}

			cliente = CCliente.readUnique(cpf);

			if (cliente == null) {
				System.out.println("Cliente não encontrado.\n");
				return;
			}
			
			imovelAdd.setCliente(cliente);

			CImovel.create(imovelAdd);
			System.out.println("Imóvel cadastrado com sucesso.\n");
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("\nErro: Imóvel com mesmo endereço já cadastrado.\n");
		} catch (SQLException e) {
			System.out.println("\nErro: " + e.getMessage() + "\n");
		} catch (Exception e) {
			System.out.println("\nErro: " + e.getMessage() + "\n");
		}

	}

	private static void editarImovel() {
		String option = null;
		do {
			System.out.println("1 - Alterar Endereço\n2 - Alterar Morador\n3 - Sair");
			System.out.print("Selecione: ");

			option = scanner.nextLine();

			if (Main.isInt(option)) {
				switch (option) {
				case "1": {
					alteracaoImovel(option);
				}
					break;
				case "2": {
					alteracaoImovel(option);
				}
					break;
				case "3":
					System.out.print("Voltando ao menu de clientes.\n");
					break;
				default:
					System.out.println("Opção inválida. Tente novamente.\n");
				}
			} else {
				System.out.println("Somente números.\n");
			}
		} while (!option.equals("3"));

	}

	private static void alteracaoImovel(String tipoAlteracao) {
		try {
			Imovel imovel = null;
			Cliente cliente = null;
			Validations validationCpf = new Validations();

			System.out.print("Infome a matrícula: ");
			String matricula = scanner.nextLine();

			imovel = CImovel.readUnique(matricula);

			if (imovel == null) {
				System.out.println("Imóvel não encontrado.\n");
				return;
			}

			if (tipoAlteracao.equals("1")) {
				System.out.print("Digite novo endereço: ");
				String alteracao = scanner.nextLine();

				CImovel.update(imovel, alteracao, tipoAlteracao);
				System.out.println("Imóvel editado com sucesso.\n");

			} else {
				System.out.print("Infome o CPF (XXX.XXX.XXX-XX) do novo morador: ");
				String cpfNovoMorador = scanner.nextLine();

				if (!validationCpf.isValidCpf(cpfNovoMorador)) {
					System.out.println("CPF inválido.\n");
					return;
				}

				cliente = CCliente.readUnique(cpfNovoMorador);
				
				if (cliente == null) {
					System.out.println("Morador não encontrado.\n");
					return;
				}
				
				String alteracao = cliente.getCpf()
;
				CImovel.update(imovel, alteracao, tipoAlteracao);

				System.out.println("Imóvel editado com sucesso.\n");
			}
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("\nErro: Imóvel com mesmo endereço já cadastrado.\n");
		} catch (SQLException e) {
			System.out.println("\nErro: " + e.getMessage() + "\n");
		} catch (Exception e) {
			System.out.println("\nErro: " + e.getMessage() + "\n");
		}
	}

	private static void excluirImovel() {
		try {
			Imovel imovel = null;

			System.out.print("Matrícula do imóvel: ");
			String matricula = scanner.nextLine();

			imovel = CImovel.readUnique(matricula);

			if (imovel == null) {
				System.out.println("Imóvel não encontrado.\n");
				return;
			}

			CImovel.delete(imovel);

			System.out.println("Cliente deletado com sucesso.\n");
		} catch (SQLException e) {
			System.out.println("\nErro: " + e.getMessage() + "\n");
		} catch (Exception e) {
			System.out.println("\nErro: " + e.getMessage() + "\n");
		}
	}

	private static void listarImoveis() {
		try {

			List<Imovel> imoveis = null;
			imoveis = CImovel.readAll();

			if (imoveis.isEmpty()) {
				System.out.println("Nenhum imóvel cadastrado.\n");
				return;
			}

			System.out.println("------------- Todos os Imóveis -------------\n");
			for (Imovel imovel : imoveis) {
				imovel.exibirInformacoes();
			}
			System.out.println("-----------------------------------------------");
		} catch (SQLException e) {
			System.out.println("\nErro: " + e.getMessage() + "\n");
		} catch (Exception e) {
			System.out.println("\nErro: " + e.getMessage() + "\n");
		}
	}

	private static void listarImoveisPorCliente() {
		try {

			Validations validationCpf = new Validations();
			List<Imovel> imoveis = null;
			Cliente cliente = null;

			System.out.print("Infome o CPF do morador: ");
			String cpfMorador = scanner.nextLine();

			if (!validationCpf.isValidCpf(cpfMorador)) {
				System.out.println("CPF inválido.\n");
				return;
			}

			cliente = CCliente.readUnique(cpfMorador);

			if (cliente == null) {
				System.out.println("Cliente não encontrado.\n");
				return;
			}

			imoveis = CImovel.readAllPerClient(cliente);

			if (imoveis == null) {
				System.out.println("Esse cliente não possui imóveis cadastrados.\n");
				return;
			}

			System.out.println("------------- Imóveis do Cliente -------------\n");
			cliente.exibirInformacoes();
			System.out.println();
			for (Imovel imovel : imoveis) {
				imovel.exibirInformacoes();
			}
			System.out.println("-----------------------------------------------");
		} catch (SQLException e) {
			System.out.println("\nErro: " + e.getMessage() + "\n");
		} catch (Exception e) {
			System.out.println("\nErro: " + e.getMessage() + "\n");
		}
	}

}
package util;

import java.util.ArrayList;
import java.util.List;

import modelos.VariavelTO;

public class SeparaListas {

	/**
	 * listaRetorno.add(notNull);
	 * 
	 * listaRetorno.add(naoNotNull);
	 * 
	 * listaRetorno.add(entrada);
	 * 
	 * listaRetorno.add(saida);
	 * 
	 * listaRetorno.add(where);
	 * 
	 * listaRetorno.add(pag);
	 * 
	 * @param lista
	 * @return
	 */
	public List<List<VariavelTO>> separarListas(List<VariavelTO> lista) {

		List<List<VariavelTO>> listaRetorno = new ArrayList<List<VariavelTO>>();

		List<VariavelTO> notNull = new ArrayList<VariavelTO>();
		List<VariavelTO> naoNotNull = new ArrayList<VariavelTO>();
		List<VariavelTO> entrada = new ArrayList<VariavelTO>();
		List<VariavelTO> saida = new ArrayList<VariavelTO>();
		List<VariavelTO> where = new ArrayList<VariavelTO>();
		List<VariavelTO> pag = new ArrayList<VariavelTO>();

		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).isNotNull()) {
				notNull.add(lista.get(i));
			} else {
				naoNotNull.add(lista.get(i));
			}

			if (lista.get(i).isEntrada()) {
				entrada.add(lista.get(i));
			}

			if (lista.get(i).isSaida()) {
				saida.add(lista.get(i));
			}

			if (lista.get(i).isWhere()) {
				where.add(lista.get(i));
			}

			if (lista.get(i).isPaginacao()) {
				pag.add(lista.get(i));
			}

		}

		listaRetorno.add(notNull);
		listaRetorno.add(naoNotNull);
		listaRetorno.add(entrada);
		listaRetorno.add(saida);
		listaRetorno.add(where);
		listaRetorno.add(pag);

		return listaRetorno;

	}

}

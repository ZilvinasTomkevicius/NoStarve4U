using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BusinessEntities
{
    public class RecipeProductsEntity
    {
        public int RecipeID { get; set; }
        public List<int> ProductIDList { get; set; }

        public RecipeProductsEntity()
        {
        }

        public RecipeProductsEntity(int recipeID, List<int> productsIDList)
        {
            RecipeID = recipeID;
            ProductIDList = productsIDList;
        }
    }
}

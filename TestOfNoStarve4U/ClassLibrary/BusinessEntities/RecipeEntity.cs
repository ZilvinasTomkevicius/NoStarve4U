using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using BusinessEntities;

namespace BusinessEntities
{
    public class RecipeEntity
    {
        /// <summary>
        /// Identificator of Recipe
        /// </summary>
        public int ID { get; set; }
        public string Name { get; set; }
        public string Description { get; set; }
        public int CookingTime { get; set; }
        public List<ProductEntity> Ingredients { get; set; }
        
        public RecipeEntity()
        {
        }

        public RecipeEntity(int id, string name, string description, int cookingTime, List<ProductEntity> ingridients)
        {
            this.ID = id;
            this.Name = name;
            this.Description = description;
            this.CookingTime = cookingTime;
            this.Ingredients = ingridients;
        }      
    }
}
